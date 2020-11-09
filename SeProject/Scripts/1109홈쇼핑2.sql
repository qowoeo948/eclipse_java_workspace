/*top2*/
select *from topcategory where topcategory_id=(유저가 선택한 subcategory_id의 topcategory_id);
select *from topcategory from subcategory  where subcategory_id =선택한 id;

select *from topcategory where topcategory_id =(select topcategory_id from subcategory where subcategory_id=5);


/*sub2*/
select *from subcategory s  where subcategory_id = subcategory_id 