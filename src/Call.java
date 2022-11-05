package okhttp3;

import java.io.IOException;
import kotlin.Metadata;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0004\n\002\030\002\n\002\020\032\n\000\n\002\020\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\bf\030\0002\0020\001:\001\021J\b\020\002\032\0020\003H&J\b\020\004\032\0020\000H&J\020\020\005\032\0020\0032\006\020\006\032\0020\007H&J\b\020\b\032\0020\tH&J\b\020\n\032\0020\013H&J\b\020\f\032\0020\013H&J\b\020\r\032\0020\016H&J\b\020\017\032\0020\020H&¨\006\022"}, d2 = {"Lokhttp3/Call;", "", "cancel", "", "clone", "enqueue", "responseCallback", "Lokhttp3/Callback;", "execute", "Lokhttp3/Response;", "isCanceled", "", "isExecuted", "request", "Lokhttp3/Request;", "timeout", "Lokio/Timeout;", "Factory", "okhttp"})
public interface Call extends Cloneable {
  @NotNull
  Request request();
  
  @NotNull
  Response execute() throws IOException;
  
  void enqueue(@NotNull Callback paramCallback);
  
  void cancel();
  
  boolean isExecuted();
  
  boolean isCanceled();
  
  @NotNull
  Timeout timeout();
  
  @NotNull
  Call clone();
  
  @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\026\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\bæ\001\030\0002\0020\001J\020\020\002\032\0020\0032\006\020\004\032\0020\005H&¨\006\006"}, d2 = {"Lokhttp3/Call$Factory;", "", "newCall", "Lokhttp3/Call;", "request", "Lokhttp3/Request;", "okhttp"})
  public static interface Factory {
    @NotNull
    Call newCall(@NotNull Request param1Request);
  }
}


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/Call.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */