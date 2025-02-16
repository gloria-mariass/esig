<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://jakarta.ee/xml/ns/jakartaee"
      xmlns:f="http://jakarta.ee/xml/ns/jakartaee">

<h:head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Leitura de Excel</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 600px;
            padding: 20px;
            text-align: center;
        }
        h2 {
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
        }
        .btn-link {
            display: inline-block;
            padding: 12px 25px;
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
            font-size: 18px;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .btn-link:hover {
            background-color: #45a049;
        }
    </style>
</h:head>

<h:body>
    <div class="container">
        <h2>Leitura de Dados da Planilha Excel</h2>
        <!-- Exemplo de botões ou links para navegação -->
        <p>Os dados da planilha foram carregados com sucesso! Agora você pode acessar a tabela de salários dos usuários.</p>

        <!-- Link para acessar a tabela de salários -->

        <a href="salario" style="font-size: 18px; padding: 10px 20px; background-color: #4CAF50; color: white; border-radius: 5px; text-decoration: none; display: inline-block; transition: background-color 0.3s;">▶ Ir para a Tabela de Salários</a>
    </div>
</h:body>
</html>
