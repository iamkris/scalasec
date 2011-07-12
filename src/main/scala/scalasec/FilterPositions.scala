package scalasec

import javax.servlet.Filter

/**
 * @author Luke Taylor
 */

private[scalasec] object FilterPositions extends Enumeration {
  val CHANNEL_FILTER,
    CONCURRENT_SESSION_FILTER,
    SECURITY_CONTEXT_FILTER,
    LOGOUT_FILTER,
    X509_FILTER,
    PRE_AUTH_FILTER,
    CAS_FILTER,
    FORM_LOGIN_FILTER,
    OPENID_FILTER,
    LOGIN_PAGE_FILTER,
    DIGEST_AUTH_FILTER,
    BASIC_AUTH_FILTER,
    REQUEST_CACHE_FILTER,
    SERVLET_API_SUPPORT_FILTER,
    JAAS_API_SUPPORT_FILTER,
    REMEMBER_ME_FILTER,
    ANONYMOUS_FILTER,
    SESSION_MANAGEMENT_FILTER,
    EXCEPTION_TRANSLATION_FILTER,
    FILTER_SECURITY_INTERCEPTOR,
    SWITCH_USER_FILTER = Value

  def comparePositions (a: Tuple2[FilterPositions.Value,Filter], b: Tuple2[FilterPositions.Value,Filter]) = {
    a._1 < b._1
  }

}
