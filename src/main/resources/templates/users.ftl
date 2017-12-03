<#ftl encoding='UTF-8'>
<#-- @ftlvariable name="users" type="java.util.List<ru.kpfu.itis.gymapp.models.User>" -->
<#import "spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Pimp yourself</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link href="http://coreplusdemo.lorvent.com/vendors/jquerylabel/css/jquery-labelauty.css"
          rel="stylesheet"
          type="text/css"/>
    <link href="http://coreplusdemo.lorvent.com/css/buttons_sass.css"
          rel="stylesheet"
          type="text/css"/>
    <link type="text/css"
          href="http://coreplusdemo.lorvent.com/A.css,,_app.css+css,,_custom.css+vendors,,_simple-line-icons,,_css,,_simple-line-icons.css+css,,_custom_css,,_user_profile.css,Mcc.YDRojCQWPN.css.pagespeed.cf.f_4TVswlNK.css"
          rel="stylesheet"/>
    <link type="text/css"
          href="http://coreplusdemo.lorvent.com/css,_app.css+vendors,_datatables,_css,_dataTables.bootstrap.css+css,_custom.css.pagespeed.cc.cBVNmYlWhB.css"
          rel="stylesheet">
    <link href="<@spring.url "/css/style.css"/>" type="text/css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>

    <script src="http://coreplusdemo.lorvent.com/js/app.js.pagespeed.jm.Xs4nyYH02x.js" type="text/javascript"></script>

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

        .panel-primary {
            background-color: #fff;
        }

        .table {
            background-color: #fff;
        }

        .content {
            height: 91.5vh;
        }

    </style>
</head>
<body>

<#include "/header.ftl">

<section class="content">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-primary">

                <div class="panel-heading">
                    <h4 class="panel-title">Users List</h4>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <div id="table_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">

                            <div class="row">
                                <div class="col-sm-12">
                                    <table class="table table-bordered dataTable no-footer" id="table" role="grid"
                                           aria-describedby="table_info">
                                        <thead>
                                        <tr class="filters" role="row">
                                            <th class="sorting_asc" tabindex="0" aria-controls="table" rowspan="1"
                                                colspan="1" aria-sort="ascending"
                                                aria-label="First Name: activate to sort column descending"
                                                style="width: 85px;">First Name
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="table" rowspan="1"
                                                colspan="1"
                                                aria-label="Last Name: activate to sort column ascending"
                                                style="width: 83px;">E-mail
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="table" rowspan="1"
                                                colspan="1"
                                                aria-label="User E-mail: activate to sort column ascending"
                                                style="width: 249px;">User Xp
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="table" rowspan="1"
                                                colspan="1" aria-label="Status: activate to sort column ascending"
                                                style="width: 51px;">Status
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="table" rowspan="1"
                                                colspan="1"
                                                aria-label="Created At: activate to sort column ascending"
                                                style="width: 84px;">Phone
                                            </th>
                                            <th class="sorting" tabindex="0" aria-controls="table" rowspan="1"
                                                colspan="1" aria-label="Actions: activate to sort column ascending"
                                                style="width: 60px;">Actions
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if users??>
                                            <#list users as user>
                                            <tr role="row">
                                                <td>${user.name}</td>
                                                <td>${user.login}</td>
                                                <td>${user.xp}</td>
                                                <td>${user.state}</td>
                                                    <td>${user.phone!'-'}</td>
                                                <td><a href="<@spring.url "/admin/users/edit/${user.id}"/>"><i
                                                        class="fa fa-fw fa-pencil text-primary actions_icon"
                                                        title="Edit User"></i></a>
                                                    <a href="<@spring.url "/admin/users/delete/${user.id}"/>"><i
                                                            class="fa fa-fw fa-times text-danger actions_icon"
                                                            title="Delete User"></i>
                                                    </a>
                                                    <a href="<@spring.url "/admin/users/profile/${user.id}"/>"><i
                                                            class="fa fa-fw fa-star text-success actions_icon"
                                                            title="View User"></i></a></td>
                                            </tr>
                                            </#list>
                                        </#if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>