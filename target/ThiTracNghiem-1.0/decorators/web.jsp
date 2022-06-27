<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trắc nghiệm online</title>
    <link rel="stylesheet" href="<c:url value='/template/webb/assets/css/base.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/template/webb/assets/css/style.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/template/webb/assets/fonts/fontawesome-free-5.15.4/css/all.min.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/template/webb/assets/fonts/themify-icons/themify-icons.css'/>"/>
    <!-- Dựng base CSS -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap">
      <!-- sweetalert -->
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>
    <div id="main">
        <%@ include file="/common/web/header.jsp" %>
        
        
		<dec:body/>
		
		
  		<%@ include file="/common/web/footer.jsp" %>
    </div>


    <script>
   
    const modal = document.querySelector('.js-modal')
    const modalContainer = document.querySelector('.js-modal-container')
    const modalClose = document.querySelector('.js-modal-close')

    function hideForm() {
        if (emailId || pwConfirmId) {
            hideFormRegister()
        } else {
            hideFormLogIn()
        }
    }


    function showFormLogIn() {
        modal.classList.add('open')
    }

    function hideFormLogIn() {
        modal.classList.remove('open')
    }

    function showFormRegister() {
        hideFormLogIn()
        emailId.classList.add('open')
        pwConfirmId.classList.add('open')
        showFormLogIn()
    }

    function hideFormRegister() {
        emailId.classList.remove('open')
        pwConfirmId.classList.remove('open')
        hideFormLogIn()
    }

    </script>
</body>

</html>