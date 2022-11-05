/*    */ package okhttp3.internal.http2;
/*    */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000*\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\b\n\002\b\004\n\002\020\013\n\002\b\005\b\b\030\000 \0232\0020\001:\001\023B\027\b\026\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003¢\006\002\020\005B\027\b\026\022\006\020\002\032\0020\006\022\006\020\004\032\0020\003¢\006\002\020\007B\025\022\006\020\002\032\0020\006\022\006\020\004\032\0020\006¢\006\002\020\bJ\t\020\013\032\0020\006HÆ\003J\t\020\f\032\0020\006HÆ\003J\035\020\r\032\0020\0002\b\b\002\020\002\032\0020\0062\b\b\002\020\004\032\0020\006HÆ\001J\023\020\016\032\0020\0172\b\020\020\032\004\030\0010\001HÖ\003J\t\020\021\032\0020\nHÖ\001J\b\020\022\032\0020\003H\026R\020\020\t\032\0020\n8\006X\004¢\006\002\n\000R\020\020\002\032\0020\0068\006X\004¢\006\002\n\000R\020\020\004\032\0020\0068\006X\004¢\006\002\n\000¨\006\024"}, d2 = {"Lokhttp3/internal/http2/Header;", "", "name", "", "value", "(Ljava/lang/String;Ljava/lang/String;)V", "Lokio/ByteString;", "(Lokio/ByteString;Ljava/lang/String;)V", "(Lokio/ByteString;Lokio/ByteString;)V", "hpackSize", "", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "okhttp"})
/*    */ public final class Header {
/*    */   @JvmField
/*    */   public final int hpackSize;
/*    */   @JvmField
/*    */   @NotNull
/*    */   public final ByteString name;
/*    */   @JvmField
/*    */   @NotNull
/*    */   public final ByteString value;
/*    */   @JvmField
/*    */   @NotNull
/*    */   public static final ByteString PSEUDO_PREFIX;
/*    */   @NotNull
/*    */   public static final String RESPONSE_STATUS_UTF8 = ":status";
/*    */   @NotNull
/*    */   public static final String TARGET_METHOD_UTF8 = ":method";
/*    */   @NotNull
/*    */   public static final String TARGET_PATH_UTF8 = ":path";
/*    */   
/* 22 */   public Header(@NotNull ByteString name, @NotNull ByteString value) { this.name = name; this.value = value;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     this.hpackSize = 32 + this.name.size() + this.value.size(); } @NotNull public static final String TARGET_SCHEME_UTF8 = ":scheme"; @NotNull public static final String TARGET_AUTHORITY_UTF8 = ":authority"; @JvmField @NotNull public static final ByteString RESPONSE_STATUS; @JvmField @NotNull public static final ByteString TARGET_METHOD; @JvmField @NotNull public static final ByteString TARGET_PATH; @JvmField @NotNull
/*    */   public static final ByteString TARGET_SCHEME; @JvmField
/*    */   @NotNull
/* 31 */   public static final ByteString TARGET_AUTHORITY; public Header(@NotNull String name, @NotNull String value) { this(ByteString.Companion.encodeUtf8(name), ByteString.Companion.encodeUtf8(value)); }
/*    */   
/* 33 */   public Header(@NotNull ByteString name, @NotNull String value) { this(name, ByteString.Companion.encodeUtf8(value)); } @NotNull
/*    */   public String toString() {
/* 35 */     return this.name.utf8() + ": " + this.value.utf8();
/*    */   } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\t\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000R\020\020\005\032\0020\0048\006X\004¢\006\002\n\000R\016\020\006\032\0020\007XT¢\006\002\n\000R\020\020\b\032\0020\0048\006X\004¢\006\002\n\000R\016\020\t\032\0020\007XT¢\006\002\n\000R\020\020\n\032\0020\0048\006X\004¢\006\002\n\000R\016\020\013\032\0020\007XT¢\006\002\n\000R\020\020\f\032\0020\0048\006X\004¢\006\002\n\000R\016\020\r\032\0020\007XT¢\006\002\n\000R\020\020\016\032\0020\0048\006X\004¢\006\002\n\000R\016\020\017\032\0020\007XT¢\006\002\n\000¨\006\020"}, d2 = {"Lokhttp3/internal/http2/Header$Companion;", "", "()V", "PSEUDO_PREFIX", "Lokio/ByteString;", "RESPONSE_STATUS", "RESPONSE_STATUS_UTF8", "", "TARGET_AUTHORITY", "TARGET_AUTHORITY_UTF8", "TARGET_METHOD", "TARGET_METHOD_UTF8", "TARGET_PATH", "TARGET_PATH_UTF8", "TARGET_SCHEME", "TARGET_SCHEME_UTF8", "okhttp"})
/*    */   public static final class Companion {
/*    */     private Companion() {} }
/* 39 */   public static final Companion Companion = new Companion(null); static { PSEUDO_PREFIX = ByteString.Companion.encodeUtf8(":");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 47 */     RESPONSE_STATUS = ByteString.Companion.encodeUtf8(":status");
/* 48 */     TARGET_METHOD = ByteString.Companion.encodeUtf8(":method");
/* 49 */     TARGET_PATH = ByteString.Companion.encodeUtf8(":path");
/* 50 */     TARGET_SCHEME = ByteString.Companion.encodeUtf8(":scheme");
/* 51 */     TARGET_AUTHORITY = ByteString.Companion.encodeUtf8(":authority"); }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final ByteString component1() {
/*    */     return this.name;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public final ByteString component2() {
/*    */     return this.value;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public final Header copy(@NotNull ByteString name, @NotNull ByteString value) {
/*    */     Intrinsics.checkNotNullParameter(name, "name");
/*    */     Intrinsics.checkNotNullParameter(value, "value");
/*    */     return new Header(name, value);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/*    */     return ((this.name != null) ? this.name.hashCode() : 0) * 31 + ((this.value != null) ? this.value.hashCode() : 0);
/*    */   }
/*    */   
/*    */   public boolean equals(@Nullable Object paramObject) {
/*    */     if (this != paramObject) {
/*    */       if (paramObject instanceof Header) {
/*    */         Header header = (Header)paramObject;
/*    */         if (Intrinsics.areEqual(this.name, header.name) && Intrinsics.areEqual(this.value, header.value))
/*    */           return true; 
/*    */       } 
/*    */     } else {
/*    */       return true;
/*    */     } 
/*    */     return false;
/*    */   }
/*    */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/http2/Header.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */