package com.klindziuk.flowershop.service.factory;

import com.klindziuk.flowershop.service.AdminService;
import com.klindziuk.flowershop.service.ClientService;
import com.klindziuk.flowershop.service.impl.AdminServiceImpl;
import com.klindziuk.flowershop.service.impl.ClientServiceImpl;

public class ServiceFactory {

    private final ClientService clientService = new ClientServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

}
