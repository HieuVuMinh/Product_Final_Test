<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Create Product</title>
    <style>
        .box-1 input#search1 {
            width: 300px;
            height: 50px;
            background: #2b303b;
            border: none;
            font-size: 10pt;
            float: left;
            color: #63717f;
            padding-left: 25px;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
        }

        body {
            font-size: 25px;
        }

        #container {
            width: 100%;
            height: 1500px;
            --borderWidth: 3px;
            position: relative;
            border-radius: var(--borderWidth);
        }

        #container:after {
            content: '';
            position: absolute;
            top: calc(-1 * var(--borderWidth));
            left: calc(-1 * var(--borderWidth));
            height: calc(100% + var(--borderWidth) * 2);
            width: calc(100% + var(--borderWidth) * 2);
            background: linear-gradient(60deg, #f79533, #f37055, #ef4e7b, #a166ab, #5073b8, #1098ad, #07b39b, #6fba82);
            border-radius: calc(2 * var(--borderWidth));
            z-index: -1;
            animation: animatedgradient 3s ease alternate infinite;
            background-size: 300% 300%;
        }

        h1 {
            text-align: center;
            margin-bottom: 80px;
            padding-top: 10px;
            color: #20232b;
        }

        img {
            width: 100px;
            height: 100px;
        }

        .col-0 {
            width: 10%;
        }

        [class*="col-"] {
            float: left;
            padding: 15px;
            justify-content: center;
            text-align: center;
        }

        /*input*/
        .form__group {
            position: relative;
            padding: 15px 0 0;
            margin-top: 10px;
            margin-left: 35%;
            width: 30%;
        }

        .form__field {
            font-family: inherit;
            width: 100%;
            border: 0;
            border-bottom: 2px solid #cdc7c7;
            outline: 0;
            font-size: 1.3rem;
            color: black;
            padding: 7px 0;
            background: transparent;
            transition: border-color 0.2s;
        }

        ::placeholder {
            color: transparent;
            text-align: center;
        }

        :placeholder-shown ~ .form__label {
            font-size: 1.3rem;
            cursor: text;
            top: 20px;
        }


        .form__label {
            position: absolute;
            top: 0;
            display: block;
            transition: 0.2s;
            font-size: 1rem;
            color: #402626;
        }

        .form__field:focus {
        ~ . form__label position: absolute;
            top: 0;
            display: block;
            transition: 0.2s;
            font-size: 1rem;
            font-weight: 700;

            padding-bottom: 6px;
            font-weight: 700;
            border-width: 3px;
            border-image-slice: 1;
        }

        /* reset input */
        .form__field
        :required, :invalid {
            box-shadow: none;
        }

        /*button*/
        .wrapper {
            position: relative;
            width: 100%;
            height: 100%;
        }

        button {
            font-family: 'Ubuntu', sans-serif;
            position: absolute;
            top: 7%;
            left: 50%;
            transform: translate(-50%, -50%);

            width: 170px;
            height: 40px;
            line-height: 1;
            font-size: 18px;
            font-weight: bold;
            letter-spacing: 1px;
            border: 3px solid #8C82FC;
            background: #fff;
            color: #8C82FC;
            border-radius: 40px;
            cursor: pointer;
            overflow: hidden;
            transition: all .35s;
        }

        button:hover {
            background: #8C82FC;
            color: #fff;
        }

        button span {
            opacity: 1;
            visibility: visible;
            transition: all .35s;
        }

        .success svg {
            width: 20px;
            height: 20px;
            fill: yellowgreen;
            transform-origin: 50% 50%;
            transform: translateY(-50%) rotate(0deg) scale(0);
            transition: all .35s;
        }

        button.is_active .success svg {
            margin-top: 50%;
            transform: translateY(-50%) rotate(720deg) scale(1);
        }

        button.is_active span {
            opacity: 0;
            visibility: hidden;
        }

        /*select option*/
        details {
            position: relative;
            width: 300px;
            margin-right: 1rem;
        }

        details[open] {
            z-index: 1;
        }

        summary {
            padding: 1rem;
            cursor: pointer;
            border-radius: 5px;
            background-color: #efefef;
            list-style: none;
        }

        summary::-webkit-details-marker {
            display: none;
        }

        details[open] summary:before {
            content: '';
            display: block;
            width: 100vw;
            height: 100vh;
            background: transparent;
            position: fixed;
            top: 0;
            left: 0;
        }

        summary:after {
            content: '';
            display: inline-block;
            float: right;
            width: .5rem;
            height: .5rem;
            border-bottom: 1px solid currentColor;
            border-left: 1px solid currentColor;
            border-bottom-left-radius: 2px;
            transform: rotate(45deg) translate(50%, 0%);
            transform-origin: center center;
            transition: transform ease-in-out 100ms
        }

        summary:focus {
            outline: none;
        }

        details[open] summary:after {
            transform: rotate(-45deg) translate(0%, 0%);
        }

        ul.list {
            width: 100%;
            background: #ddd;
            position: absolute;
            top: calc(100% + .5rem);
            left: 0;
            padding: 1rem;
            margin: 0;
            box-sizing: border-box;
            border-radius: 5px;
            max-height: 200px;
            overflow-y: auto;
        }

        li.list {
            margin: 0;
            padding: 1rem 0;
            border-bottom: 1px solid #ffffff;
        }

        li.list:first-child {
            padding-top: 0;
        }

        li.list:last-child {
            padding-bottom: 0;
            border-bottom: none;
        }

        summary.radios {
            counter-reset: radios;
        }

        input[type=radio] {
            counter-increment: radios;
            appearance: none;
            display: none;
        }

        input[type=radio]:checked {
            display: inline;
        }

        input[type=radio]:after {
            content: attr(title);
            display: inline;
            font-size: 1rem;
        }

        ul.list {
            counter-reset: labels;
        }

        label {
            width: 100%;
            display: block;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div id="container">
    <div id="title">
        <h1><b>Thêm Sản Phẩm</b></h1>
    </div>
    <form method="post">
        <div id="body">
            <div class="form__group field">
                <input type="input" class="form__field" placeholder="Name" name="name" id='name' required/>
                <label for="name" class="form__label">Name</label>
            </div>
            <div class="form__group field">
                <input type="number" class="form__field" placeholder="Price" name="price" id='price' required/>
                <label for="price" class="form__label">Price</label>
            </div>
            <div class="form__group field">
                <input type="number" class="form__field" placeholder="Quantity" name="quantity" id='quantity'
                       required/>
                <label for="quantity" class="form__label">Quantity</label>
            </div>
            <div class="form__group field">
                <input type="input" class="form__field" placeholder="Color" name="color" id='color' required/>
                <label for="color" class="form__label">Color</label>
            </div>
            <div class="form__group field">
                <input type="input" class="form__field" placeholder="Description" name="description" id='description' required/>
                <label for="description" class="form__label">Description</label>
            </div>

            <div class="form__group field">
                <details class="custom-select" style="width: 217px">
                    <summary class="radios" style="width: 417px; height: auto; background-color: #ffffff; ">
                        <input type="radio" name="category" id="default" title="Category" style=" font-style: inherit"  checked>
                        <c:forEach items='${requestScope["category"]}' var="category">
                            <input type="radio" name="category" id="${category.getId()}" title="${category.getName()}" value="${category.getId()}" >
                        </c:forEach>
                    </summary>
                    <ul class="list" style="list-style: none; font-size: 19px; background-color: white">
                        <c:forEach items='${requestScope["category"]}' var="category">
                            <li>
                                <label for="${category.getId()}">${category.getName()}</label>
                            </li>
                        </c:forEach>
                    </ul>
                </details>
<%--                <input type="number" class="form__field" placeholder="Category" name="category" id='category' required/>--%>
<%--                <label for="category" class="form__label">Category</label>--%>
            </div>


            <div class="wrapper">
                <button type="submit">
                    <span>Create Product</span>
                </button>
            </div>

            <div>
                <button type="submit">
                    <a href="/products" style="text-decoration: none">Back</a>
                </button>
            </div>

        </div>
    </form>
</div>
</body>
</html>
