<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <p>
        你好，${name}同学，欢迎加入Xxx大家庭！您的入职信息如下：
    </p>
    <table border="1" cellspacing="0">
        <tr><td><strong style = "color:#F00">工号</strong></td><td>${workId}</td></tr>
        <tr><td><strong style="color: #F00">合同期限</strong></td><td>${contractTerm}年</td></tr>
        <tr><td><strong style="color: #F00">合同起始日期</strong></td><td>${beginContract?string("yyyy-MM-dd")}</td></tr>
        <tr><td><strong style="color: #F00">合同截至日期</strong></td><td>${endContract?string("yyyy-MM-dd")}</td></tr>
        <tr><td><strong style="color: #F00">所属部门</strong></td><td>${departmentName}</td></tr>
        <tr><td><strong style="color: #F00">职位</strong></td><td>${posName}</td></tr>
    </table>
<p><strong style = "color: #FF0000;font-size: 24px;">希望在未来的日子里，携手共进！</strong></p>

</body>
</html>