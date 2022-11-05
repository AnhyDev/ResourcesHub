/*    */ package okhttp3.internal.http2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import kotlin.Metadata;
/*    */ import kotlin.jvm.JvmField;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
/*    */ import okio.BufferedSource;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0008\n\002\030\002\n\002\020\000\n\000\n\002\020\013\n\000\n\002\020\b\n\000\n\002\030\002\n\002\b\004\n\002\020 \n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\030\002\n\002\b\002\bf\030\000 \0242\0020\001:\001\024J(\020\002\032\0020\0032\006\020\004\032\0020\0052\006\020\006\032\0020\0072\006\020\b\032\0020\0052\006\020\t\032\0020\003H&J&\020\n\032\0020\0032\006\020\004\032\0020\0052\f\020\013\032\b\022\004\022\0020\r0\f2\006\020\t\032\0020\003H&J\036\020\016\032\0020\0032\006\020\004\032\0020\0052\f\020\017\032\b\022\004\022\0020\r0\fH&J\030\020\020\032\0020\0212\006\020\004\032\0020\0052\006\020\022\032\0020\023H&¨\006\025"}, d2 = {"Lokhttp3/internal/http2/PushObserver;", "", "onData", "", "streamId", "", "source", "Lokio/BufferedSource;", "byteCount", "last", "onHeaders", "responseHeaders", "", "Lokhttp3/internal/http2/Header;", "onRequest", "requestHeaders", "onReset", "", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "Companion", "okhttp"})
/*    */ public interface PushObserver
/*    */ {
/*    */   @JvmField
/*    */   @NotNull
/*    */   public static final PushObserver CANCEL;
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\b\003\030\0002\0020\001:\001\005B\007\b\002¢\006\002\020\002R\026\020\003\032\0020\0048\006X\004ø\001\000¢\006\002\n\000¨\006\001\002\007\n\005\bF0\001¨\006\006"}, d2 = {"Lokhttp3/internal/http2/PushObserver$Companion;", "", "()V", "CANCEL", "Lokhttp3/internal/http2/PushObserver;", "PushObserverCancel", "okhttp"})
/*    */   public static final class Companion
/*    */   {
/*    */     private Companion() {}
/*    */     
/*    */     @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\0008\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\020\b\n\000\n\002\030\002\n\002\b\004\n\002\020 \n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\030\002\n\000\b\002\030\0002\0020\001B\005¢\006\002\020\002J(\020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\b2\006\020\t\032\0020\0062\006\020\n\032\0020\004H\026J&\020\013\032\0020\0042\006\020\005\032\0020\0062\f\020\f\032\b\022\004\022\0020\0160\r2\006\020\n\032\0020\004H\026J\036\020\017\032\0020\0042\006\020\005\032\0020\0062\f\020\020\032\b\022\004\022\0020\0160\rH\026J\030\020\021\032\0020\0222\006\020\005\032\0020\0062\006\020\023\032\0020\024H\026¨\006\025"}, d2 = {"Lokhttp3/internal/http2/PushObserver$Companion$PushObserverCancel;", "Lokhttp3/internal/http2/PushObserver;", "()V", "onData", "", "streamId", "", "source", "Lokio/BufferedSource;", "byteCount", "last", "onHeaders", "responseHeaders", "", "Lokhttp3/internal/http2/Header;", "onRequest", "requestHeaders", "onReset", "", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "okhttp"})
/*    */     private static final class PushObserverCancel
/*    */       implements PushObserver
/*    */     {
/*    */       public boolean onRequest(int streamId, @NotNull List requestHeaders) {
/* 79 */         Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders"); return true;
/*    */       }
/*    */       
/*    */       public boolean onHeaders(int streamId, @NotNull List responseHeaders, boolean last) {
/* 83 */         Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders"); return true;
/*    */       }
/*    */ 
/*    */       
/*    */       public boolean onData(int streamId, @NotNull BufferedSource source, int byteCount, boolean last) throws IOException {
/* 88 */         Intrinsics.checkNotNullParameter(source, "source"); source.skip(byteCount);
/* 89 */         return true;
/*    */       }
/*    */       
/*    */       public void onReset(int streamId, @NotNull ErrorCode errorCode) {
/* 93 */         Intrinsics.checkNotNullParameter(errorCode, "errorCode");
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static final Companion Companion = new Companion(null);
/*    */   
/*    */   boolean onRequest(int paramInt, @NotNull List<Header> paramList);
/*    */   
/*    */   boolean onHeaders(int paramInt, @NotNull List<Header> paramList, boolean paramBoolean);
/*    */   
/*    */   boolean onData(int paramInt1, @NotNull BufferedSource paramBufferedSource, int paramInt2, boolean paramBoolean) throws IOException;
/*    */   
/*    */   void onReset(int paramInt, @NotNull ErrorCode paramErrorCode);
/*    */   
/*    */   static {
/*    */     CANCEL = new Companion.PushObserverCancel();
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/PushObserver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */