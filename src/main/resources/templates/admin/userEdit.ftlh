<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User editor</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-body border-bottom shadow-sm">
    <h5 class="h5 my-0 me-md-auto fw-normal">Employee of the Month</h5>
    <nav class="my-2 my-md-0 me-md-3 ml-2">
        <a class="p-2 text-dark" href="/">Main page</a>
        <a class="p-2 text-dark" href="/vacancy">Vacancies</a>
        <a class="p-2 text-dark" href="/support">Support</a>
        <a class="p-2 text-dark" href="/about">About us</a>
        <a class="p-2 text-dark" href="/user">User editor</a>
    </nav>
    <form class="ml-2" action="@{/logout}" method="post">
        <input type="submit" value="Sign out" class="btn btn-outline-primary ml-2">
    </form>
</div>

<div class="container mt-5 mb-5">
    <form action="/user" method="post">
        <label for="inputUsername" class="visually-hidden">Username</label>
        <input id="inputUsername" type="text" maxlength="20" value="${user.username}" name="username" required=""
               class="form-control mb-3" placeholder="Username">
        <#list roles as role>
            <div class="mb-3">
                <label><input class="mr-1" type="checkbox" name="${role}" ${user.roles ? seq_contains(role) ?
                    string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-success" type="submit">Save</button>
    </form>
</div>

<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#">Back to top</a>
        </p>
        <p class="mb-1">Employment agency example. ©GeorgProg.</p>
        <p class="mb-0">New user? <a href="/">Visit the homepage</a> or read our <a
                    href="#">Support</a>.</p>
    </div>
</footer>

</body>
</html>