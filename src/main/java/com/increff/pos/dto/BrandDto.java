package com.increff.pos.dto;

import com.increff.pos.model.BrandData;
import com.increff.pos.model.BrandForm;
import com.increff.pos.pojo.BrandPojo;
import com.increff.pos.service.ApiException;
import com.increff.pos.service.BrandService;
import com.increff.pos.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class BrandDto {

    @Autowired
    private BrandService brandService;

    public void addBrand(BrandForm brandForm) throws ApiException {
        BrandPojo brandPojo = ConvertUtil.convertBrandFormtoBrandPojo(brandForm);
        brandService.add(brandPojo);
    }

    public List<BrandData> searchBrandData(BrandForm brandForm)  {
        BrandPojo brandPojo = ConvertUtil.convertBrandFormtoBrandPojo(brandForm);
        List<BrandPojo> brandPojoList = brandService.search(brandPojo);
        List<BrandData> brandDataList = new ArrayList<BrandData>();
        for (BrandPojo brandPojo1 : brandPojoList) {
            brandDataList.add(ConvertUtil.convertBrandPojotoBrandData(brandPojo1));
        }
        return brandDataList;
    }

    public BrandPojo getByBrandCategory(BrandForm brandForm) throws ApiException {
        BrandPojo brandPojo = ConvertUtil.convertBrandFormtoBrandPojo(brandForm);
        return brandService.searchBrandCategory(brandPojo);
    }

    public BrandData getBrandData(int id) throws ApiException {
        return ConvertUtil.convertBrandPojotoBrandData(brandService.get(id));
    }

    public List<BrandData> getAllBrand(){
        List<BrandPojo> brandPojoList = brandService.getAll();
        List<BrandData> brandDataList = new ArrayList<BrandData>();
        for(BrandPojo brandPojo : brandPojoList) {
            brandDataList.add(ConvertUtil.convertBrandPojotoBrandData(brandPojo));
        }
        return brandDataList;
    }

    public void updateBrand(int id, BrandForm brandForm) throws ApiException {
        BrandPojo brandPojo = ConvertUtil.convertBrandFormtoBrandPojo(brandForm);
        brandService.update(id, brandPojo);
    }

    public void deleteBrand(int id) {
        brandService.delete(id);
    }
}
