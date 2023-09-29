package com.dev.products.productservice.repositories;

public interface CustomQueries {
    String deleteproductbyId = "Delete from Products where id =" ;

    String FIND_CATEGORY_BY_NAME  = "Select * from Category where name =:categoryName";
    String FIND_ALL_CATEGORY = "Select * from Product where category_id=:categoryId";

}
