<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- some css -->
      <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <section class="py-2 min-vh-100 d-flex align-items-center" style="background-color: #eee;">
            <div class="container ">
                <div class="row d-flex justify-content-center align-items-center">
                    <div class="col-xl-10">
                        <div class="card rounded-3 text-black">
                            <div class="row g-0">
                                    <div class="card-body p-md-5 mx-md-4">

                                        <div class="text-center">
                                            <h4 class="mt-1 mb-5 pb-1">Task management application</h4>
                                        </div>

                                        <form action="MainController" method="POST">
                                            <input type="hidden" name="action" value="login">
                                            <p>Please login to your account</p>
                                            <c:if test="${not empty error}">
                                                <p>${error}</p>
                                            </c:if>
                                            <div data-mdb-input-init class="form-outline mb-4">
                                                <label class="form-label" for="form2Example11" >Username</label>
                                                <input type="email" id="form2Example11" class="form-control"
                                                       placeholder="Enter email address" name="username"/>
                                            </div>

                                            <div data-mdb-input-init class="form-outline mb-4">
                                                <label class="form-label" for="form2Example22" >Password</label>
                                                <input type="password" id="form2Example22" class="form-control" name="password"/>
                                            </div>

                                            <div class="text-center pt-1 mb-5 pb-1">
                                                <button data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block fa-lg gradient-custom-2 mb-3" type="submit">Log
                                                    in</button>
                                                <a class="text-muted" href="#!">Forgot password?</a>
                                            </div>

                                            <div class="d-flex align-items-center justify-content-center pb-4">
                                                <p class="mb-0 me-2">Don't have an account?</p>
                                                <a href="MainController?action=register">
                                                    <button  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-outline-danger">Register</button>
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
