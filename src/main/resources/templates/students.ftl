<html>
<head></head>
<body>
<table>
<#list students as students>
<tr>
<td>${students.name}</td><td>${students.age}</td><td>${students.sex}</td>
</tr>
</#list>
</table>
</body>
</html>