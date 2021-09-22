package init.learn.repo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import init.learn.model.Product;
@Repository
public interface Productrepo extends JpaRepository<Product,Integer> {
	
	/* Using findBy in repo findByName and we need to specify Productname in CommandLineRunner"*/
    List<Product> findByProductName(String productName);
    
   
	/*it wil get the data which is Product vendor and we are getting the data by productVendor */
	List<Product> findByProductVendor(String productVendor);
	
    
	/* it will get the data  which is null*/
	List<Product> findByProductCostIsNotNull();
    
	/* findBy in repo is finding by the cost which  is null*/
	List<Product> findByProductCostIsNull();
	
	
	/*here findBy repo is used to find the which is more than 100.0*/
	List<Product> findByProductCostGreaterThan(double ProductCost);

	
	
	/*here reserve key word of Equals is use for the Productcost which is equals to what we specified*/
	List<Product> findByProductCostEquals(double ProductCost);
	
	
	/*Here iam using the key word of "Starting" for productvendor which is starts with "P"*/
	List<Product> findByProductVendorStartingWith(String Productven);

	/*Here iam using the key word of "Ending" for productvendor which is Ends with "y"*/
	List<Product> findByProductVendorEndingWith(String ProdVen);
	 
	/*Here iam using the key word of "Containing" for productvendor which is have "e"*/
	List<Product> findByProductVendorIsContaining(String ProdVen);
	
	
	/*Here iam using key word is Like and using % for find i specified middel letter*/
	List<Product> findByProductVendorLike(String ProductVendor);
	
	/*Here iam using the key word for  getting the  data in order by findByProductId */
	List<Product>   findByProductIOrderByProductCodeDesc(Integer ProductId,String ProductCode);
	
	
	/*Here iam using the key word for getting the  data  by "Between" */
	List<Product> findByProductCostBetween(double Productcosts,double Procost);
	
	/*Here iam using the key word for getting the data by or */
	List<Product> findByProductIdOrProductCode(Integer ProductId,String ProductCost);
	
	
	/*Here iam using the key word for getting the data by NotOr*/
	List<Product> findByProuductIdNotOrProductCost(Integer ProductId,String ProductCode);
     

	/*For getting random data using ProductIdin*/
	List<Product> findByProductIdIn(Collection<Integer> values);

	
	/*for getting the data which is false in the database*/
	List<Product> findByStatusTrue();
	
	
	/*for getting the data which is false in the database */
	List<Product> findByStatusFalse();
    
	
	/*for gettinng the data which is Before the date*/
	List<Product> findByMfgDateBefore(Date dae);
	
	
	/*for getting the data which is  After the date*/
	List<Product> findByMfgDateAfter(Date dte);

	
   //List<Product> findByCurrentDate(Date Dte);
	
	
	                                             /*PROJECTIONS*/
	
	/*projections is nothing but fetching the required columns*/
	/*static projections---onece columns and variables are prrovided then everytime it will display's*/
	
    List<Myview> findByProductVendor1(String ProductVendor); 
	
   interface Myview{
	   Integer getProductId();
	   String getProductCode();
	   String getProductCost();
	  
	
	/*Dynamic view type it can specify the view type while executing the query */
		
	interface Myview1{
		Integer getProductId();
		   String getProductCode();
		   String getProductCost();	 
	List<Myview> findByProductVendor(String string, Class<Myview> clz);
	    
   
	   /*                 				@QUERY                                             */
	
	/*@Query now its standard way of implementation and it is in the form of insstensive
	 * it dont support the *
	 * the word "select" should be in the form of capital (or) small whatever
	 * the word "from" should be in the form of capital (or) small whatever
	 * it wont support the "*" */
	@Query("SELECT p from init.learn.model.Product p where p.productVendor=:a or p.productCode=:b ")
	List<Product> getProductData(@Param("a")String ProductVendor,@Param("b")String ProductCode);
	
	
	/*Here now the query is unstandard 
	 * without using package as table name
	 * without using @Param also it works*/
	@Query("FROM Product p where p.productVendor=:a or p.productCode=:b")
	List<Product> getProductData1(@Param("a")String a,@Param("b")String b);
	
	
	//*This is the way to represent the query NATIVEQUREY WHICH IS NOTHING BUT PURE SQL
	/**/
	@Query(value="SELECT * from product where productVendor=:a or productCode=:b",nativeQuery=true)
	List<Product> getProductData11(@Param("a")String ProductVendor,@Param("b")String ProductCode);
    
	
	                     /*single columns
	                      *multiple columns
	                      *all columns 
	                      *USING @QUERY */
/*   ALL COLUMNS */	
	@Query("select p  from init.learn.model.Product p")
	List<Product> getAllProductInfo();
	

/*  FEW COLUMNS*/
	@Query("select  p.productVendor,p.productCode from init.learn.model.Product p")
	List<Object[]> getAllProductVendorAndProductCode(String vendor,String code);

	
/* ONE COLUMN */
  	@Query("select p.productVendor from Product p")
	List<String> getAllProductVendor(String vendor);	
	
	             /*  USING OPERATORS  IN @QUERY*/
	
	/*Here iam using the like operator which is between "b" and productVendor not equals what i given
	 */
	@Query("select p.productCode from Product p where p.productCode like:p.productCode or p.productVendor!=p.productVendosr")
	List<String> getMyInfo(@Param("productCode")String ProductCode,@Param("productVendor")String Productvendor);
     
	
	
	/*Here using @Query we need to get the few columns
	 * EXAMPLE
	 * product cost some value to another particular value along with the productCode details also
	 * Here i used for few Columns <Object[]> */
	@Query("select p.productCode,p.productCost from Product p where p.productCost between :v1 and :v2")
	List<Object[]> getMyInfoB(@Param("v1")double productCode,@Param("v2")double productCost);
	
	
	
	/*Here iam using not in Query for the products which are not in what i given list */
	@Query("select p from Product p where p.productCost not in (:inputs)")
	List<Product> getInfoc(@Param("inputs")Collection<Double> inputs);
    
	
	/*                     Non select operations                               */
	
	//@Transactional //for commit and rollback
	//@Modifying     //non-select operation
	/*deleting the cost which is the product what is specified*/
	/*@Query("Delete From Product p where productCost>:product AND productCode IS NOT NULL")*/
	Integer removeData(@Param("product")double prodcost);
	/*             update non select operation*/
	@Query("Update Product set productCost=:product where productVendor=:vendor")
	Integer updateData(@Param("product")double product);
	
}
   }

   Iterable<Product> findByCurrentDate(Date date);
}
