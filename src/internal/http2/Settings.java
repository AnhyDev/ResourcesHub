/*    */ package okhttp3.internal.http2;
/*    */ 
/*    */ import kotlin.Metadata;
/*    */ import kotlin.collections.ArraysKt;
/*    */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*    */ import kotlin.jvm.internal.Intrinsics;
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
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000*\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\006\n\002\020\025\n\000\n\002\020\002\n\002\b\003\n\002\020\013\n\002\b\013\030\000 \0332\0020\001:\001\033B\005¢\006\002\020\002J\006\020\f\032\0020\rJ\021\020\016\032\0020\0042\006\020\017\032\0020\004H\002J\016\020\020\032\0020\0212\006\020\022\032\0020\021J\006\020\023\032\0020\004J\016\020\024\032\0020\0042\006\020\022\032\0020\004J\016\020\025\032\0020\0042\006\020\022\032\0020\004J\016\020\026\032\0020\0212\006\020\017\032\0020\004J\016\020\027\032\0020\r2\006\020\030\032\0020\000J\031\020\t\032\0020\0002\006\020\017\032\0020\0042\006\020\031\032\0020\004H\002J\006\020\032\032\0020\004R\021\020\003\032\0020\0048F¢\006\006\032\004\b\005\020\006R\021\020\007\032\0020\0048F¢\006\006\032\004\b\b\020\006R\016\020\t\032\0020\004X\016¢\006\002\n\000R\016\020\n\032\0020\013X\004¢\006\002\n\000¨\006\034"}, d2 = {"Lokhttp3/internal/http2/Settings;", "", "()V", "headerTableSize", "", "getHeaderTableSize", "()I", "initialWindowSize", "getInitialWindowSize", "set", "values", "", "clear", "", "get", "id", "getEnablePush", "", "defaultValue", "getMaxConcurrentStreams", "getMaxFrameSize", "getMaxHeaderListSize", "isSet", "merge", "other", "value", "size", "Companion", "okhttp"})
/*    */ public final class Settings
/*    */ {
/*    */   private int set;
/* 28 */   private final int[] values = new int[10]; public static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535; public static final int HEADER_TABLE_SIZE = 1;
/*    */   public static final int ENABLE_PUSH = 2;
/*    */   public static final int MAX_CONCURRENT_STREAMS = 4;
/*    */   
/*    */   public final int getHeaderTableSize() {
/* 33 */     int bit = 2;
/* 34 */     return ((bit & this.set) != 0) ? this.values[1] : -1;
/*    */   }
/*    */   public static final int MAX_FRAME_SIZE = 5; public static final int MAX_HEADER_LIST_SIZE = 6; public static final int INITIAL_WINDOW_SIZE = 7; public static final int COUNT = 10; public static final Companion Companion = new Companion(null);
/*    */   
/*    */   public final int getInitialWindowSize() {
/* 39 */     int bit = 128;
/* 40 */     return ((bit & this.set) != 0) ? this.values[7] : 65535;
/*    */   }
/*    */   
/*    */   public final void clear() {
/* 44 */     this.set = 0;
/* 45 */     ArraysKt.fill$default(this.values, 0, 0, 0, 6, null);
/*    */   }
/*    */   @NotNull
/*    */   public final Settings set(int id, int value) {
/* 49 */     if (id < 0 || id >= this.values.length) {
/* 50 */       return this;
/*    */     }
/*    */     
/* 53 */     int bit = 1 << id;
/* 54 */     this.set |= bit;
/* 55 */     this.values[id] = value;
/* 56 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public final boolean isSet(int id) {
/* 61 */     int bit = 1 << id;
/* 62 */     return ((this.set & bit) != 0);
/*    */   }
/*    */   
/*    */   public final int get(int id) {
/* 66 */     return this.values[id];
/*    */   }
/*    */   public final int size() {
/* 69 */     return Integer.bitCount(this.set);
/*    */   }
/*    */   
/*    */   public final boolean getEnablePush(boolean defaultValue) {
/* 73 */     int bit = 4;
/* 74 */     return ((bit & this.set) != 0) ? ((this.values[2] == 1)) : defaultValue;
/*    */   }
/*    */   
/*    */   public final int getMaxConcurrentStreams() {
/* 78 */     int bit = 16;
/* 79 */     return ((bit & this.set) != 0) ? this.values[4] : Integer.MAX_VALUE;
/*    */   }
/*    */   
/*    */   public final int getMaxFrameSize(int defaultValue) {
/* 83 */     int bit = 32;
/* 84 */     return ((bit & this.set) != 0) ? this.values[5] : defaultValue;
/*    */   }
/*    */   
/*    */   public final int getMaxHeaderListSize(int defaultValue) {
/* 88 */     int bit = 64;
/* 89 */     return ((bit & this.set) != 0) ? this.values[6] : defaultValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void merge(@NotNull Settings other) {
/* 97 */     Intrinsics.checkNotNullParameter(other, "other"); for (byte b1 = 0, b2 = 10; b1 < b2; b1++) {
/* 98 */       if (other.isSet(b1))
/* 99 */         set(b1, other.get(b1)); 
/*    */     } 
/*    */   }
/*    */   
/*    */   @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\b\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\004XT¢\006\002\n\000R\016\020\006\032\0020\004XT¢\006\002\n\000R\016\020\007\032\0020\004XT¢\006\002\n\000R\016\020\b\032\0020\004XT¢\006\002\n\000R\016\020\t\032\0020\004XT¢\006\002\n\000R\016\020\n\032\0020\004XT¢\006\002\n\000R\016\020\013\032\0020\004XT¢\006\002\n\000¨\006\f"}, d2 = {"Lokhttp3/internal/http2/Settings$Companion;", "", "()V", "COUNT", "", "DEFAULT_INITIAL_WINDOW_SIZE", "ENABLE_PUSH", "HEADER_TABLE_SIZE", "INITIAL_WINDOW_SIZE", "MAX_CONCURRENT_STREAMS", "MAX_FRAME_SIZE", "MAX_HEADER_LIST_SIZE", "okhttp"})
/*    */   public static final class Companion {
/*    */     private Companion() {}
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/Settings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */