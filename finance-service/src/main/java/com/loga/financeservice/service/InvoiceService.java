package com.loga.financeservice.service;

import com.loga.financeservice.app.api.CustomerServiceProxy;
import com.loga.financeservice.app.api.InvoiceServiceProxy;
import com.loga.financeservice.app.api.MaintenanceServiceProxy;
import com.loga.financeservice.app.factory.*;
import com.loga.financeservice.entity.Invoice;
import com.loga.financeservice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class InvoiceService implements IInvoiceService{

    private final InvoiceServiceProxy mecef;
    private final MaintenanceServiceProxy maintenanceServiceProxy;
    private final CustomerServiceProxy customerServiceProxy;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceServiceProxy mecef,
                          MaintenanceServiceProxy maintenanceServiceProxy,
                          CustomerServiceProxy customerServiceProxy, InvoiceRepository invoiceRepository) {
        this.mecef = mecef;
        this.maintenanceServiceProxy = maintenanceServiceProxy;
        this.customerServiceProxy = customerServiceProxy;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice create(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void invoicing(Long id) throws IOException, InterruptedException {
        Repair repair = maintenanceServiceProxy.retrieveRepair(id);
        Dossier dossier = customerServiceProxy.retrieveDossier(repair.getDossier());

        InvoiceRequestDataDto invoiceRequestDataDto = new InvoiceRequestDataDto();

        invoiceRequestDataDto.setClient(new ClientDto(
                dossier.getClient().getLegal_notice(),
                dossier.getClient().getName(),
                dossier.getClient().getContact(),
                dossier.getClient().getAddress()
        ));

        invoiceRequestDataDto.setReference(repair.getReference());
        invoiceRequestDataDto.setType("FV");
        invoiceRequestDataDto.setOperator(new OperatorDto(null,"SFE_MECEF"));
        invoicing(invoiceRequestDataDto);
    }

    @Override
    public void invoicing(String reference) throws IOException, InterruptedException {
        Repair repair = maintenanceServiceProxy.retrieveRepair(reference);
        Dossier dossier = customerServiceProxy.retrieveDossier(repair.getDossier());

        InvoiceRequestDataDto invoiceRequestDataDto = new InvoiceRequestDataDto();

        invoiceRequestDataDto.setClient(new ClientDto(
                dossier.getClient().getLegal_notice(),
                dossier.getClient().getName(),
                dossier.getClient().getContact(),
                dossier.getClient().getAddress()
        ));

        Invoice invoice = new Invoice();

        for (Task task: repair.getTasks()) {
            invoice.addItem(task);
        }

        invoiceRequestDataDto.setReference(repair.getReference());
        invoiceRequestDataDto.setType("FV");
        invoiceRequestDataDto.setOperator(new OperatorDto(null,"SFE_MECEF"));
        invoicing(invoiceRequestDataDto);
    }

    public void invoicing(InvoiceRequestDataDto invoiceRequestDataDto){
        InvoiceResponseDataDto invoiceResponse = mecef.invoicing(invoiceRequestDataDto);

        if(invoiceResponse!=null){
            SecurityElementsDto securityElementsDto = mecef.confirmation(invoiceResponse.getUid(),"confirm");
        }
    }
}
