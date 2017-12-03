<#ftl encoding='UTF-8'>
<#import "spring.ftl" as spring />
<#-- @ftlvariable name="phoneForm" type="ru.kpfu.itis.gymapp.forms.UserPhoneForm" -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pimp Yourself</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>

    <script src="http://coreplusdemo.lorvent.com/js/app.js.pagespeed.jm.Xs4nyYH02x.js" type="text/javascript"></script>


    <link type="text/css"
          href="http://coreplusdemo.lorvent.com/A.css,,_app.css+vendors,,_jasny-bootstrap,,_css,,_jasny-bootstrap.css+vendors,,_select2,,_css,,_select2.min.css+vendors,,_select2,,_css,,_select2-bootstrap.css+vendors,,_bootstrapvalidator,,_css,,_bootstrapValidator.min.css+vendors,,_datetimepicker,,_css,,_bootstrap-datetimepicker.min.css,Mcc.iJBuO24LbF.css.pagespeed.cf.wwL0jY3rho.css"
          rel="stylesheet"/>
    <link href="http://coreplusdemo.lorvent.com/css/buttons_sass.css"
          rel="stylesheet"
          type="text/css"/>
    <link type="text/css"
          href="http://coreplusdemo.lorvent.com/A.css,,_app.css+css,,_custom.css+vendors,,_simple-line-icons,,_css,,_simple-line-icons.css+css,,_custom_css,,_user_profile.css,Mcc.YDRojCQWPN.css.pagespeed.cf.f_4TVswlNK.css"
          rel="stylesheet"/>
    <link href="<@spring.url "/css/style.css"/>"
          type="text/css"
          rel="stylesheet">


    <style>
        .content .panel {
            padding: 2em;
            background-color: #f2f1e6;
            border-radius: 3px;
            -webkit-box-shadow: 0 1px 2px rgba(88, 118, 124, .6);
            box-shadow: 0 1px 2px rgba(88, 118, 124, .6);
        }

        .content .panel {
            padding: 3em;
            margin: 2em 3em;
            border-radius: 2em;
        }

        .content {
            height: 91.5vh;
        }

        .panel-heading {
            margin-bottom: 2em;
        }
    </style>

</head>
<body>

<#include "/header.ftl">

<section class="content">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-primary">

                <div class="panel-heading col-md-12">
                    <h3 class="panel-title text-center">Phone settings</h3>
                </div>

                <div class="panel-body">
                    <form id="adduser_form" method="POST" action="<@spring.url "/setting/phone"/>"
                          class="form-horizontal bv-form"
                          novalidate="novalidate">
                    <#if errors??>
                        <#list errors as error>
                            <div class="text-danger">* <@spring.message "${error.code}"/></div>
                        </#list>
                    </#if>
                        <div class="form-group">
                            <label for="phone" class="col-md-2 control-label">Phone</label>
                            <div class="col-md-10">
                                <input id="phone" name="phone" placeholder="+7 XXX XXX XX XX" type="text"
                                       class="form-control required" data-bv-field="phone"  value=
                                <#if phoneForm??>
                                    <#if phoneForm.phone??>
                                            "${phoneForm.phone}"
                                    </#if>
                                </#if>
                                >
                            </div>
                        </div>
                        <input type="submit"
                               class="center-block finish-button button button button-3d button-primary button-rounded btn_3d"
                               value="Save">
                    </form>

                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>