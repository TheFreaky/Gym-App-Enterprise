<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pimp Yourself</title>

    <script src="http://coreplusdemo.lorvent.com/js/app.js.pagespeed.jm.Xs4nyYH02x.js"
            type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"></script>
    <link href="http://coreplusdemo.lorvent.com/css/buttons_sass.css"
          rel="stylesheet"
          type="text/css"/>


    <link href="<c:url value= "/static/css/scrolling-nav.css"/>" rel="stylesheet" type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>


    <style>
        .parent {
            background-color: #3d4e5d;
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0;
            left: 0;
            overflow: auto;
        }

        .signin-button {
            margin-top: 1.5em;
        }

        .welcome-panel {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        .logo {
            margin: auto;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            width: 50%;
            height: 50%;
        }

        .logo {
            width: 497px;
        }

        .modal-backdrop {
            z-index: -1;
        }
    </style>

</head>

<body>

<section class="content parent">
    <div class="container d-flex h-100">
        <div class="row welcome-panel">
            <div class="logo">
                <a href=""><img src="<c:url value= "/static/img/logo.png"/>" alt="logo"/></a>
            </div>
            <div class="signin-button">
                <a class="text-center nav-link js-scroll-trigger">
                    <button
                            class="center-block finish-button button button button-3d button-primary button-rounded btn_3d"
                            href="#home" data-toggle="modal" data-target=".modal"
                    >Войти
                    </button>
                </a>
            </div>
        </div>
    </div>
</section>

<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="form-body">
                <div class="row">

                    <ul class="nav nav-tabs final-login" role="tablist">
                        <li role="presentation" class="active">
                            <a href="#signin" aria-controls="signin" role="tab" data-toggle="tab"
                               class="flex-sm-fill text-sm-center nav-link">Войти</a>
                        </li>
                        <li role="presentation">
                            <a href="#signup" aria-controls="signin" role="tab" data-toggle="tab"
                               class="flex-sm-fill text-sm-center nav-link">Регистрация</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="signin" class="tab-pane active" role="tabpanel">
                            <div class="inner-form">
                                <form class="sign-form" method="post" action="<c:url value= "/login"/>">
                                    <label for="signin-username-field">Email:</label>
                                    <input id="signin-username-field" type="text" name="login">
                                    <label for="signin-password-field">Пароль:</label>
                                    <input id="signin-password-field" type="password" name="password">
                                    <label for="signin-remember-me">Remember me</label>
                                    <input id="signin-remember-me" type="checkbox" name="remember-me">
                                    <div>
                                        <button type="submit">Войти</button>
                                        <a href="">Забыли пароль?</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div id="signup" class="tab-pane" role="tabpanel">
                            <div class="inner-form">
                                <form:form method="POST" action="/signup" commandName="userForm" cssClass="sign-form">
                                    <form:errors path="*" cssClass="text-danger"/><br>
                                    <form:label path="phone">Имя:</form:label>
                                    <form:input id="signup-name" path="name"/>
                                    <form:label path="login">Email:</form:label>
                                    <form:input id="signup-username" path="login"/>
                                    <form:label path="password">Пароль:</form:label>
                                    <form:password id="signup-password" path="password"/>

                                    <button type="submit">Зарегистрироваться</button>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>