package com.github.kazuki43zoo.app.common.flow.address;

import com.github.kazuki43zoo.app.common.flow.CommonScreenFlowController;
import com.github.kazuki43zoo.domain.model.StreetAddress;
import com.github.kazuki43zoo.domain.repository.address.StreetAddressSearchCriteria;
import com.github.kazuki43zoo.domain.service.address.StreetAddressService;
import org.dozer.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@RequestMapping("commonFlow/streetAddresses")
@CommonScreenFlowController
public class StreetAddressController {

    @Inject
    StreetAddressService streetAddressService;

    @Inject
    Mapper beanMapper;

    @ModelAttribute
    public StreetAddressSearchForm setupSearchForm() {
        return new StreetAddressSearchForm();
    }

    @RequestMapping(method = RequestMethod.GET, params = "searchForm")
    public String searchForm() {
        return "commonFlow/streetAddress/searchForm";
    }

    @RequestMapping(method = RequestMethod.GET, params = "searchRedo")
    public String searchRedo(StreetAddressSearchForm form) {
        return searchForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String search(
            @Validated StreetAddressSearchForm form,
            BindingResult bindingResult,
            @PageableDefault(size = 5) Pageable pageable,
            Model model) {
        if (bindingResult.hasErrors()) {
            return searchForm();
        }
        StreetAddressSearchCriteria criteria = beanMapper.map(form, StreetAddressSearchCriteria.class);
        Page<StreetAddress> page = streetAddressService.search(criteria, pageable);
        model.addAttribute("page", page);
        return "commonFlow/streetAddress/searchResult";
    }

}