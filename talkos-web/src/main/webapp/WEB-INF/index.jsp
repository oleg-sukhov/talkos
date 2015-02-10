<!doctype html>
<html lang="en" ng-app="talkos">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/talkos.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.css">
</head>
<body>

<div ng-cloak ng-view></div>

<script src="${pageContext.request.contextPath}/resources/bower_components/angular/angular.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/angular-route/angular-route.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/angular-cookies/angular-cookies.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/bower_components/angular-bootstrap/ui-bootstrap-tpls.js"></script>

<!--custom scripts-->
<script src="${pageContext.request.contextPath}/resources/js/app/app.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app/login/login.controller.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/app/home/home.controller.js"></script>
</body>
</html>