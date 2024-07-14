<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Neon Light Box Loader</title>
    <link rel="stylesheet" href="main.css">
    </head>
<body>
    <div class="button" id="enterButton">ENTER</div>
    
<script>
    document.getElementById("enterButton").addEventListener("click", function() {
        window.location.href = "startload.jsp";
    });
</script>
</body>
</html>