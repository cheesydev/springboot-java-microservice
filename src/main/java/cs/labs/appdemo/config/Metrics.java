package cs.labs.appdemo.config;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;

public class Metrics {

    public static MeterFilter addPercentilesToProductsEndpointTimers() {
        return new MeterFilter() {
            @Override
            public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
                if (id.getName().startsWith("products")) {

                    return DistributionStatisticConfig.builder()
                            .percentiles(0.99, 0.9, 0.75, 0.5)
                            .build()
                            .merge(config);
                }
                return config;
            }
        };
    }
}
