/*    */ package okhttp3;
/*    */ 
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okio.ByteString;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000<\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\016\n\002\b\003\n\002\020\003\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\b&\030\0002\0020\001B\005¢\006\002\020\002J \020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\b2\006\020\t\032\0020\nH\026J \020\013\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\b2\006\020\t\032\0020\nH\026J\"\020\f\032\0020\0042\006\020\005\032\0020\0062\006\020\r\032\0020\0162\b\020\017\032\004\030\0010\020H\026J\030\020\021\032\0020\0042\006\020\005\032\0020\0062\006\020\022\032\0020\nH\026J\030\020\021\032\0020\0042\006\020\005\032\0020\0062\006\020\023\032\0020\024H\026J\030\020\025\032\0020\0042\006\020\005\032\0020\0062\006\020\017\032\0020\020H\026¨\006\026"}, d2 = {"Lokhttp3/WebSocketListener;", "", "()V", "onClosed", "", "webSocket", "Lokhttp3/WebSocket;", "code", "", "reason", "", "onClosing", "onFailure", "t", "", "response", "Lokhttp3/Response;", "onMessage", "text", "bytes", "Lokio/ByteString;", "onOpen", "okhttp"})
/*    */ public abstract class WebSocketListener
/*    */ {
/*    */   public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
/* 26 */     Intrinsics.checkNotNullParameter(webSocket, "webSocket"); Intrinsics.checkNotNullParameter(response, "response");
/*    */   }
/*    */   
/*    */   public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
/* 30 */     Intrinsics.checkNotNullParameter(webSocket, "webSocket"); Intrinsics.checkNotNullParameter(text, "text");
/*    */   }
/*    */   
/*    */   public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
/* 34 */     Intrinsics.checkNotNullParameter(webSocket, "webSocket"); Intrinsics.checkNotNullParameter(bytes, "bytes");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
/* 40 */     Intrinsics.checkNotNullParameter(webSocket, "webSocket"); Intrinsics.checkNotNullParameter(reason, "reason");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
/* 47 */     Intrinsics.checkNotNullParameter(webSocket, "webSocket"); Intrinsics.checkNotNullParameter(reason, "reason");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
/* 55 */     Intrinsics.checkNotNullParameter(webSocket, "webSocket"); Intrinsics.checkNotNullParameter(t, "t");
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/WebSocketListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */