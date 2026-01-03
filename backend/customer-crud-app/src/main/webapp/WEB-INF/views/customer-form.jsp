<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Register</title>
        <link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/customer-form.css">
</head>
<body>
<div class="container">

	<c:choose>
		<c:when test="${mode == 'CREATE'}">
    		<div class="form-title">
        		<h2>Customer Registration</h2>
        		<small>Register new customer details</small>
    		</div>
    	</c:when>
    	<c:otherwise>
    		<div class="form-title">
        		<h2>Update Customer</h2>
        		<small>Modify existing customer information</small>
    		</div>
    	</c:otherwise>
    </c:choose>

    <form:form	modelAttribute="customer"
    			action="${mode == 'CREATE'
             	? pageContext.request.contextPath.concat('/customers/save')
             	: pageContext.request.contextPath.concat('/customers/update/').concat(customerId)}" method="post">

        <div class="form-grid">

            <div class="form-group">
                <label>First Name</label>
                <form:input path="firstName" placeholder="Enter first name" />
                <form:errors path="firstName" cssClass="error"/>
            </div>

            <div class="form-group">
                <label>Last Name</label>
                <form:input path="lastName" placeholder="Enter last name" />
                <form:errors path="lastName" cssClass="error"/>
            </div>

            <div class="form-group">
                <label>Date of Birth</label>
                <form:input path="dateOfBirth" type="date"/>
				<form:errors path="dateOfBirth" cssClass="error"/>
            </div>

            <div class="form-group">
                <label>Mobile Number</label>
                <form:input path="mobile" id="mobile" placeholder="Enter mobile number" />
                <form:errors path="mobile" cssClass="error"/>
            </div>

            <div class="form-group">
                <label>Email</label>
                <form:input path="email" placeholder="Enter email address" />
                <form:errors path="email" cssClass="error"/>
            </div>

            <div class="form-group">
                <label>Age</label>
                <form:input path="age" type="number" id="age" placeholder="Enter age" />
                <form:errors path="age" cssClass="error"/>
            </div>

            <div class="form-group full-width">
                <label>Address Line 1</label>
                <form:input path="address1" placeholder="Enter address line 1" />
                <form:errors path="address1" cssClass="error"/>
            </div>

            <div class="form-group full-width">
                <label>Address Line 2</label>
                <form:input path="address2" placeholder="Enter address line 2" />
                <form:errors path="address2" cssClass="error"/>
            </div>

            <div class="form-group full-width">
                <label>Gender</label>
                <div class="radio-group">
                    <form:radiobutton path="gender" value="1"/> Male
					<form:radiobutton path="gender" value="2"/> Female
					<form:radiobutton path="gender" value="3"/> Other
                </div>
                <form:errors path="gender" cssClass="error"/>
            </div>

        </div>

        <div class="button-group">

    		<c:if test="${mode == 'CREATE'}">
        		<button type="submit" class="btn btn-primary">Register</button>
    		</c:if>

    		<c:if test="${mode == 'UPDATE'}">
        		<button type="submit" class="btn btn-warning">Update</button>
    		</c:if>
    		<c:if test="${mode == 'CREATE'}">
    			<a href="${pageContext.request.contextPath}/customers/new" id="clear" class="btn btn-secondary">Clear</a>
    		</c:if>
		</div>
        <div class="back-link">
    		<a href="/">Back to Customer List</a>
		</div>

    </form:form>
<script src="/js/customer-form.js"></script>
</div>
</body>
</html>