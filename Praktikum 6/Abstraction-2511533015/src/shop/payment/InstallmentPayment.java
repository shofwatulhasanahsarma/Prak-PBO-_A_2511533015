package shop.payment;

public interface InstallmentPayment extends PaymentMethod {
    int maxInstallmentMonths();

    default long monthlyInstallment(long total, int months) {
        if (months < 1 || months > maxInstallmentMonths()) {
            throw new IllegalArgumentException(
                "tenor harus 1.." + maxInstallmentMonths() + ", diterima: " + months);
        }
        return (total + months - 1) / months; // dibulatkan ke atas
    }
}