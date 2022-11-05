package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Metadata;
import okio.Sink;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\026\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\030\002\n\000\bf\030\0002\0020\001J\b\020\002\032\0020\003H&J\b\020\004\032\0020\005H&¨\006\006"}, d2 = {"Lokhttp3/internal/cache/CacheRequest;", "", "abort", "", "body", "Lokio/Sink;", "okhttp"})
public interface CacheRequest {
  @NotNull
  Sink body() throws IOException;
  
  void abort();
}


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/cache/CacheRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */