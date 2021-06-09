<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <style>
        /*CSS cho thanh search*/
        .box{
            width: 300px;
            height: 50px;
        }
        .box-1{
            width: 300px;
            vertical-align: middle;
            white-space: nowrap;
            position: relative;
        }
        .box-1 input#search1{
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
            margin-bottom: 30px;
        }

        img {
            width: 100px;
            height: 100px;
        }

        .col-0 {
            width: 5%;
        }

        .col-1 {
            width: 18%;
        }

        .col-2 {
            width: 15%;
        }

        .col-3 {
            width: 15%;
        }

        .col-4 {
            width: 15%;
        }

        .col-5 {
            width: 9%;
        }

        .col-6 {
            width: 9%;
        }

        [class*="col-"] {
            float: left;
            padding: 15px;
            justify-content: center;
            text-align: center;
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

    </style>
</head>
<body>
<div id="container">
    <div id="title">
        <h1><b>Danh sách Sản Phẩm</b></h1>
    </div>
<%--    search--%>
    <form name="search">
        <div class="box">
            <div class="box-1">
                <span class="icon"><i class="fa fa-search"></i></span>
                <input type="search" name="search" id="search1" placeholder="Search..."/>
            </div>
        </div>
    </form>
<%--    button--%>
    <div>
        <button type="submit">
            <a href="/products?action=create" style="text-decoration: none">Create Product</a>
        </button>
    </div>

    <div id="body">
        <div>
            <div class="col-0">
                ID
            </div>
            <div class="col-1">
                Tên Sản Phẩm
            </div>
            <div class="col-2">
                Giá Sản Phẩm
            </div>
            <div class="col-3">
                Mô Tả
            </div>
            <div class="col-4">
                Nhà Sản Xuất
            </div>
            <div class="col-5">
                Chỉnh Sửa
            </div>
            <div class="col-6">
                Xóa
            </div>
        </div>
        <hr width="100%" align="center"/>
        <c:forEach items='${requestScope["products"]}' var="product">
            <div>
                <div class="col-0">
                    ${product.getIdPr()}
                </div>
                <div class="col-1">
                    <a href="/products?action=view&id=${product.getIdPr()}" style="text-decoration: none">${product.getNameProduct()}</a>
                </div>
                <div class="col-2">
                    ${product.getPricePr()} USD
                </div>
                <div class="col-3">
                    ${product.getDescriptionPr()}
                </div>
                <div class="col-4">
                    ${product.getProducerPr()}
                </div>
                <div class="col-5">
                    <a href="/products?action=edit&id=${product.idPr}" style="text-decoration: none">Chỉnh Sửa</a>
                </div>
                <div class="col-6">
                    <a href="/products?action=delete&id=${product.idPr}" style="text-decoration: none">Xóa</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
