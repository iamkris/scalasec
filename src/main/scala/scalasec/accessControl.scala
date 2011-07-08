package scalasec

import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest
import java.util.Arrays
import org.springframework.security.web.util.{AnyRequestMatcher, RequestMatcher}
import org.springframework.security.access.{AccessDecisionManager, AccessDecisionVoter, ConfigAttribute, SecurityConfig}
import org.springframework.security.access.vote.{AffirmativeBased, AuthenticatedVoter, RoleVoter}
import collection.immutable.ListMap
import java.{util => ju}
import org.springframework.security.web.access.intercept._

/**
 * Trait which contains the FilterSecurityInterceptor, AccessDecisionManager and related beans.
 *
 * @author Luke Taylor
 */
trait WebAccessControl extends FilterStack with Conversions {
  override lazy val filterSecurityInterceptor = {
    val fsi = new FilterSecurityInterceptor()
    fsi.setSecurityMetadataSource(securityMetadataSource)
    fsi.setAccessDecisionManager(accessDecisionManager)
    fsi
  }

  private[scalasec] var accessUrls : ListMap[RequestMatcher, ju.List[ConfigAttribute]] = ListMap()

  private[scalasec] def securityMetadataSource : FilterInvocationSecurityMetadataSource
          = new DefaultFilterInvocationSecurityMetadataSource(accessUrls)

  def interceptUrl(matcher: RequestMatcher, access: (Authentication, HttpServletRequest) => Boolean, channel: RequiredChannel.Value = RequiredChannel.Any) {
    addInterceptUrl(matcher, Arrays.asList(new ScalaWebConfigAttribute(access)), channel)
  }

  private[scalasec] def addInterceptUrl(matcher: RequestMatcher, attributes: ju.List[ConfigAttribute], channel: RequiredChannel.Value) {
    assert(!accessUrls.contains(matcher), "An identical RequestMatcher already exists: " + matcher)
    assert(!accessUrls.exists(_._1.isInstanceOf[AnyRequestMatcher]), "A universal match has already been included in the " +
      "list, so any further interceptUrls will have no effect")
    accessUrls = accessUrls + (matcher -> attributes)
  }

  def accessDecisionVoters : List[AccessDecisionVoter[_]] = new ScalaWebVoter :: Nil

  lazy val accessDecisionManager : AccessDecisionManager = new AffirmativeBased(Arrays.asList(accessDecisionVoters: _*))
}

/**
 * Enum containing the options for secure channel
 */
object RequiredChannel extends Enumeration {
  val Http, Https, Any = Value
}
