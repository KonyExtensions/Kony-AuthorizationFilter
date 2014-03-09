Kony-AuthorizationFilter
========================

Template Utility

This filter should be applied in web.xml for the middleware.war next to XSS Filter
 
user-role should be set in session as 'Authorized' as part of post-logon code (post-processor class). In case of multiple user roles, set user-role in session based on business logic
