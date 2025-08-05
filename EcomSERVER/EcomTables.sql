-- Drop existing DB if needed (forces deletion)
use master;

IF DB_ID('EcomDB') IS NOT NULL

BEGIN
    ALTER DATABASE EcomDB SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE EcomDB;
END
GO
-- ===========================
-- CREATE DATABASE
-- ===========================
CREATE DATABASE EcomDB;
GO
USE EcomDB;
GO

-- ===========================
-- PROFILE TABLE
-- ===========================
CREATE TABLE Profile (
    ProfileID INT IDENTITY(1,1) PRIMARY KEY,
    Name NVARCHAR(100) NOT NULL,
    Email NVARCHAR(255) NOT NULL UNIQUE,
    Password NVARCHAR(255) NOT NULL
);
GO

-- ===========================
-- ADDRESS BOOK
-- ===========================
CREATE TABLE Address (
    AddressID INT IDENTITY(1,1) PRIMARY KEY,
    ProfileID INT NOT NULL,
    FullName NVARCHAR(100) NOT NULL,
    Street NVARCHAR(255) NOT NULL,
    City NVARCHAR(100) NOT NULL,
    State NVARCHAR(100) NOT NULL,
    ZipCode NVARCHAR(20) NOT NULL,
    Country NVARCHAR(100) NOT NULL,
    Phone NVARCHAR(20) NOT NULL,
    FOREIGN KEY (ProfileID) REFERENCES Profile(ProfileID) ON DELETE CASCADE
);
GO

-- ===========================
-- PRODUCTS
-- ===========================
CREATE TABLE Products (
    ProductID INT IDENTITY(1,1) PRIMARY KEY,
    ProductName NVARCHAR(100) NOT NULL,
    Category NVARCHAR(50) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL
);
GO

-- ===========================
-- CART
-- ===========================
CREATE TABLE Cart (
    CartID INT IDENTITY(1,1) PRIMARY KEY,
    ProfileID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL DEFAULT 1,
    FOREIGN KEY (ProfileID) REFERENCES Profile(ProfileID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE
);
GO

-- ===========================
-- WISHLIST
-- ===========================
CREATE TABLE Wishlist (
    WishlistID INT IDENTITY(1,1) PRIMARY KEY,
    ProfileID INT NOT NULL,
    ProductID INT NOT NULL,
    FOREIGN KEY (ProfileID) REFERENCES Profile(ProfileID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE
);
GO

-- ===========================
-- ORDERS
-- ===========================
CREATE TABLE Orders (
    OrderID INT IDENTITY(1,1) PRIMARY KEY,
    ProfileID INT NOT NULL,
    AddressID INT NOT NULL,
    PaymentMethod NVARCHAR(50) NOT NULL,
    PaymentDetails NVARCHAR(255),
    Status NVARCHAR(50) DEFAULT 'Pending',
    OrderDate DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (ProfileID) REFERENCES Profile(ProfileID) ON DELETE CASCADE,
    FOREIGN KEY (AddressID) REFERENCES Address(AddressID)
);
GO

-- ===========================
-- ORDER ITEMS
-- ===========================
CREATE TABLE OrderItems (
    OrderItemID INT IDENTITY(1,1) PRIMARY KEY,
    OrderID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON DELETE CASCADE,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID) ON DELETE CASCADE
);
GO

-- ===========================
-- PAYMENTS (Optional)
-- ===========================
CREATE TABLE Payments (
    PaymentID INT IDENTITY(1,1) PRIMARY KEY,
    OrderID INT NOT NULL,
    PaymentMethod NVARCHAR(50) NOT NULL,
    PaymentDetails NVARCHAR(255),
    PaymentDate DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) ON DELETE CASCADE
);
GO

-- ===========================
-- SAMPLE PRODUCTS (50 items)
-- ===========================
INSERT INTO Products (ProductName, Category, Price) VALUES
-- Shoes
('Nike Air Zoom', 'Shoes', 5999.00),
('Adidas Ultraboost', 'Shoes', 7499.00),
('Puma Flyer Runner', 'Shoes', 3299.00),
('Reebok Classic', 'Shoes', 4299.00),
('Skechers Go Walk', 'Shoes', 3799.00),
('Asics Gel Kayano', 'Shoes', 8299.00),
('New Balance 574', 'Shoes', 4999.00),
('Fila Disruptor', 'Shoes', 2899.00),
('Woodland Leather Boots', 'Shoes', 6999.00),
('Bata Comfit Sneakers', 'Shoes', 2499.00),

-- Shirts
('Levis Casual Shirt', 'Shirts', 1999.00),
('US Polo Polo Shirt', 'Shirts', 1799.00),
('Allen Solly Formal Shirt', 'Shirts', 2499.00),
('Peter England Check Shirt', 'Shirts', 1599.00),
('Van Heusen Slim Fit Shirt', 'Shirts', 2299.00),
('Arrow Oxford Shirt', 'Shirts', 2599.00),
('Blackberrys Cotton Shirt', 'Shirts', 2199.00),
('Louis Philippe Linen Shirt', 'Shirts', 3499.00),
('Marks & Spencer Casual Shirt', 'Shirts', 1899.00),
('H&M Striped Shirt', 'Shirts', 1499.00),

-- Pants
('Levis Slim Fit Jeans', 'Pants', 2499.00),
('Wrangler Regular Fit Jeans', 'Pants', 2299.00),
('Lee Skinny Fit Jeans', 'Pants', 2199.00),
('Arrow Formal Trousers', 'Pants', 2799.00),
('Peter England Chinos', 'Pants', 1999.00),
('Van Heusen Dress Pants', 'Pants', 2999.00),
('H&M Relaxed Jeans', 'Pants', 1799.00),
('Zara Cargo Pants', 'Pants', 2599.00),
('Blackberrys Formal Pants', 'Pants', 3199.00),
('Gap Straight Fit Jeans', 'Pants', 2699.00),

-- Accessories
('Ray-Ban Aviator Sunglasses', 'Accessories', 7999.00),
('Titan Leather Wallet', 'Accessories', 1499.00),
('Fossil Leather Belt', 'Accessories', 1799.00),
('Casio Digital Watch', 'Accessories', 2499.00),
('Apple Watch Series 8', 'Accessories', 45999.00),
('Noise Smartwatch', 'Accessories', 5999.00),
('Boat Rockerz 255 Pro', 'Accessories', 1299.00),
('Sony WH-1000XM4', 'Accessories', 24999.00),
('Samsung Galaxy Buds 2', 'Accessories', 8999.00),
('JBL Flip 6 Speaker', 'Accessories', 11999.00),

-- Bags
('American Tourister Backpack', 'Bags', 3999.00),
('Wildcraft Rucksack', 'Bags', 3299.00),
('Skybags Laptop Bag', 'Bags', 2899.00),
('Tommy Hilfiger Duffle Bag', 'Bags', 5999.00),
('Nike Gym Sack', 'Bags', 999.00),
('Puma Sports Bag', 'Bags', 1599.00),
('Adidas Duffel Bag', 'Bags', 1899.00),
('Safari Cabin Luggage', 'Bags', 4999.00),
('VIP Check-in Luggage', 'Bags', 6999.00),
('Targus Laptop Backpack', 'Bags', 3599.00);
GO


select * from Address;
select * from Cart;
select * from Orders;
select * from OrderItems;
select * from Payments;
select * from Products;
select * from Profile;
select * from Wishlist;