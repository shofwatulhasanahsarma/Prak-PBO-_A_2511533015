package shop.shipping;

public class ExpressShipping extends ShippingService {

    private static final long RATE_PER_KG = 18_000;

    public ExpressShipping() {
        super("Ekspres");
    }

    @Override
    protected long ratePerKg() {
        return RATE_PER_KG;
    }
}