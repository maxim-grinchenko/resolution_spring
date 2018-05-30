<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<html>
<body>
<form method="post"  modelAttribute="userJSP" action="create">

    Name: <spring:input path="name"/>   <br/>
    Password: <spring:input path="password"/>   <br/>
    <spring:button>Next Page</spring:button>

</form>
</body>
</html>
