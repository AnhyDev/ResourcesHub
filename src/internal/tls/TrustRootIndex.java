package okhttp3.internal.tls;

import java.security.cert.X509Certificate;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\002\bf\030\0002\0020\001J\022\020\002\032\004\030\0010\0032\006\020\004\032\0020\003H&¨\006\005"}, d2 = {"Lokhttp3/internal/tls/TrustRootIndex;", "", "findByIssuerAndSignature", "Ljava/security/cert/X509Certificate;", "cert", "okhttp"})
public interface TrustRootIndex {
  @Nullable
  X509Certificate findByIssuerAndSignature(@NotNull X509Certificate paramX509Certificate);
}


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/tls/TrustRootIndex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */