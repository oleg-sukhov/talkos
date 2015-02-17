<!doctype html>
<html lang="en" ng-app="talkos">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/talkos.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap_spacelab.css">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.css">--%>
</head>
<body>

<div ng-cloak ng-view></div>

<script src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/angular-route/angular-route.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/angular-cookies/angular-cookies.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/ng-flow/dist/ng-flow-standalone.js"></script>

<!--custom scripts-->
<script src="${pageContext.request.contextPath}/resources/js/app/app.js"></script>
<!--Login-->
<script src="${pageContext.request.contextPath}/resources/js/app/login/login.controller.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app/login/login.js"></script>
<!--Home-->
<script src="${pageContext.request.contextPath}/resources/js/app/home/home.controller.js"></script>
<!--Registration-->
<script src="${pageContext.request.contextPath}/resources/js/app/registration/registration.controller.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app/registration/registration.js"></script>

</body>
</html>