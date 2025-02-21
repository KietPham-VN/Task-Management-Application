<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            .gradient-custom-2 {
                /* fallback for old browsers */
                background: #fccb90;

                /* Chrome 10-25, Safari 5.1-6 */
                background: -webkit-linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);

                /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
                background: linear-gradient(to right, #ee7724, #d8363a, #dd3675, #b44593);
            }

            @media (min-width: 768px) {
                .gradient-form {
                    height: 100vh !important;
                }
            }
            @media (min-width: 769px) {
                .gradient-custom-2 {
                    border-top-right-radius: .3rem;
                    border-bottom-right-radius: .3rem;
                }
            }
        </style>
    </head>

    <body>
        <%
            HashMap<String, String> formData = (HashMap<String, String>) request.getAttribute("formData");
            
            HashMap<String,String> inputError = (HashMap<String,String>) request.getAttribute("validation-error");
            String nameErr = (inputError != null) ? inputError.get("name") : "";
            String emailErr = (inputError != null) ? inputError.get("email") : "";
            String passwordErr = (inputError != null) ? inputError.get("password") : "";
            String roleErr = (inputError != null) ? inputError.get("role") : "";
        
            String invalidDataErr = (String) request.getAttribute("invalid-data-exception");
        %>

        <section class="py-2" style="background-color: #eee;">
            <div class="container">
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-xl-10">
                        <div class="card rounded-3 text-black">
                            <div class="row g-0">
                                    <div class="card-body p-md-5 mx-md-4">

                                        <div class="text-center">
                                            <h1>Register</h1>
                                        </div>

                                        <form action="RegisterController" method="POST">
                                            <div data-mdb-input-init class="form-outline mb-4">
                                                <label class="form-label" for="name">Name:</label>
                                                <input class="form-control" type="text" name="name" id="name" value="<%= (formData != null && formData.get("name") != null) ? formData.get("name") : "" %>" required>
                                                <p class="text-danger"><%=(nameErr!=null)?nameErr:""%></p>
                                            </div>

                                            <div data-mdb-input-init class="form-outline mb-4">
                                                <label class="form-label" for="email">Email:</label>
                                                <input class="form-control" type="email" name="email" id="email" value="<%= (formData != null && formData.get("email") != null) ? formData.get("email") : "" %>" required>
                                                <p class="text-danger"><%=(emailErr!=null)?emailErr:""%></p>
                                            </div>
                                            
                                            <div data-mdb-input-init class="form-outline mb-4">
                                                <label class="form-label" for="password">Password:</label>
                                                <input class="form-control" type="password" name="password" id="password" value="<%= (formData != null && formData.get("password") != null) ? formData.get("password") : "" %>" required>
                                                <p class="text-danger"><%=(passwordErr!=null)?passwordErr:""%></p>
                                            </div>
                                            
                                            <div data-mdb-input-init class="form-outline mb-4">
                                                <label class="form-label" for="role">Role:</label>
                                                <select name="role" id="role">
                                                    <option value="Team Member" <%= (formData != null && "Team Member".equals(formData.get("role"))) ? "selected" : "" %>>Team Member</option>
                                                    <option value="Project Manager" <%= (formData != null && "Project Manager".equals(formData.get("role"))) ? "selected" : "" %>>Project Manager</option>
                                                </select>
                                                <p class="text-danger"><%=(roleErr!=null)?roleErr:""%></p>
                                            </div>

                                            <p class="text-danger"><%=(invalidDataErr!=null)?invalidDataErr:""%></p>
                                            <div class="text-center pt-1 mb-5 pb-1">
                                                <input data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit" value="Register"/>
                                            </div>

                                            <div class="d-flex align-items-center justify-content-center pb-4">
                                                <p class="mb-0 me-2">Already have an account?</p>
                                                <a href="home">
                                                    <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-danger">Login</button>
                                                </a>
                                            </div>

                                        </form>

                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
