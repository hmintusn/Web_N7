package webgr7.hotelbk.service;

import webgr7.hotelbk.dto.VoucherDTO;

import webgr7.hotelbk.model.Voucher;

import java.util.List;

public interface VoucherService {
    public List<Voucher> getAllVouchers();

    public Voucher createVoucher(VoucherDTO voucherDTO);

    public Voucher getVoucherById(Long voucherId);

    public Voucher updateVoucherById(Long voucherId, VoucherDTO voucherDTO);

    public void deleteVoucherById(Long voucherId);
    public List<Voucher> getClientVoucher(Long clientId);
}
