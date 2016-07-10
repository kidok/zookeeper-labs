package x.utils;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;

public class MetricsUtil {
  public static final MetricRegistry metrics = new MetricRegistry();
  static {
    final JmxReporter reporter = JmxReporter.forRegistry(metrics).build();
    reporter.start();
  }

}
