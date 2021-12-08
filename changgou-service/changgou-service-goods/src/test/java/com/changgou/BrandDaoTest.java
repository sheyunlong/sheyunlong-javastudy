package com.changgou;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BrandDaoTest {
    @Autowired
    private BrandMapper brandMapper;

    @Test
    public void findAll(){
        //查询所有
        List<Brand> brandList = brandMapper.selectAll();
        for (Brand brand : brandList) {
            System.out.println(brand.getName() );
        }
    }
}
