package okhttp3;

import kotlin.Metadata;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0008\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\020\013\n\000\n\002\020\b\n\000\n\002\020\016\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\bf\030\0002\0020\001:\001\022J\b\020\002\032\0020\003H&J\032\020\004\032\0020\0052\006\020\006\032\0020\0072\b\020\b\032\004\030\0010\tH&J\b\020\n\032\0020\013H&J\b\020\f\032\0020\rH&J\020\020\016\032\0020\0052\006\020\017\032\0020\tH&J\020\020\016\032\0020\0052\006\020\020\032\0020\021H&¨\006\023"}, d2 = {"Lokhttp3/WebSocket;", "", "cancel", "", "close", "", "code", "", "reason", "", "queueSize", "", "request", "Lokhttp3/Request;", "send", "text", "bytes", "Lokio/ByteString;", "Factory", "okhttp"})
public interface WebSocket {
  @NotNull
  Request request();
  
  long queueSize();
  
  boolean send(@NotNull String paramString);
  
  boolean send(@NotNull ByteString paramByteString);
  
  boolean close(int paramInt, @Nullable String paramString);
  
  void cancel();
  
  @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\bæ\001\030\0002\0020\001J\030\020\002\032\0020\0032\006\020\004\032\0020\0052\006\020\006\032\0020\007H&¨\006\b"}, d2 = {"Lokhttp3/WebSocket$Factory;", "", "newWebSocket", "Lokhttp3/WebSocket;", "request", "Lokhttp3/Request;", "listener", "Lokhttp3/WebSocketListener;", "okhttp"})
  public static interface Factory {
    @NotNull
    WebSocket newWebSocket(@NotNull Request param1Request, @NotNull WebSocketListener param1WebSocketListener);
  }
}


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/WebSocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */