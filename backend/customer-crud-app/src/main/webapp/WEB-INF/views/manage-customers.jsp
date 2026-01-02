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
    <title>Customer Management – Admin Panel</title>
    <link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">
</head>
    <div class="navbar">Customer Management – Admin Panel</div>

    <div class="container">
      <div class="page-header">
        <h2>Manage Customers</h2>
  		<a href="/customers/new" class="btn btn-primary">+ Add Customer</a>
      </div>

      <div class="table-responsive">
        <table>
          <thead>
            <tr>
              <th class="col-id">ID</th>
              <th class="col-name">Name</th>
              <th class="col-address">Address</th>
              <th class="col-dob">DOB</th>
              <th class="col-mobile">Mobile</th>
              <th class="col-age">Age</th>
              <th class="col-gender">Gender</th>
              <th class="col-email">Email</th>
              <th class="col-action">Action</th>
            </tr>
          </thead>

          <tbody>
          	                <!-- If no records -->
                <c:if test="${empty customers}">
                    <tr>
                        <td colspan="9" style="text-align:center;">
                            No customer records found
                        </td>
                    </tr>
                </c:if>

                <!-- Customer records -->
                <c:forEach var="c" items="${customers}">
                    <tr>
                        <td>${c.id}</td>
                        <td title="${c.name}">${c.name}</td>
                        <td title="${c.address}">${c.address}</td>
                        <td>${c.dateOfBirth}</td>
                        <td>${c.mobile}</td>
                        <td>${c.age}</td>
                        <td>
                            <c:choose>
                                <c:when test="${c.gender == 1}">Male</c:when>
                                <c:when test="${c.gender == 2}">Female</c:when>
                                <c:otherwise>Other</c:otherwise>
                            </c:choose>
                        </td>
                        <td title="${c.email}">${c.email}</td>
                        <td>
                            <!-- Edit -->
                            <a href="/customers/edit/${c.id}"
                               class="btn btn-edit">
                                Edit
                            </a>
                            <!-- Delete -->
							<button type="button" class="btn btn-delete" onclick="confirmDelete(${c.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
 
	<!-- Delete Confirmation Modal -->
	<div id="deleteModal" class="modal">
	    <div class="modal-content">
	        <h3>Confirm Delete</h3>
	        <p>Are you sure you want to delete this customer record?</p>
	
	        <div class="modal-actions">
	            <button class="btn btn-secondary" onclick="closeModal()">Cancel</button>
	            <a id="confirmDeleteBtn" class="btn btn-delete">Delete</a>
	        </div>
	    </div>
	</div>
<script>
    function confirmDelete(customerId) {
        const modal = document.getElementById("deleteModal");
        const deleteBtn = document.getElementById("confirmDeleteBtn");

        deleteBtn.href = "/customers/delete/" + customerId;
        modal.style.display = "block";
    }

    function closeModal() {
        document.getElementById("deleteModal").style.display = "none";
    }

    // Close modal when clicking outside
    window.onclick = function(event) {
        const modal = document.getElementById("deleteModal");
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>
<body>
</body>
</html>