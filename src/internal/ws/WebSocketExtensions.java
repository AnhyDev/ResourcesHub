/*     */ package okhttp3.internal.ws;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import kotlin.Metadata;
/*     */ import kotlin.jvm.JvmField;
/*     */ import kotlin.jvm.internal.DefaultConstructorMarker;
/*     */ import kotlin.jvm.internal.Intrinsics;
/*     */ import kotlin.text.StringsKt;
/*     */ import okhttp3.Headers;
/*     */ import okhttp3.internal.Util;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000 \n\002\030\002\n\002\020\000\n\000\n\002\020\013\n\000\n\002\020\b\n\002\b\025\n\002\020\016\n\002\b\002\b\b\030\000 \0342\0020\001:\001\034BE\022\b\b\002\020\002\032\0020\003\022\n\b\002\020\004\032\004\030\0010\005\022\b\b\002\020\006\032\0020\003\022\n\b\002\020\007\032\004\030\0010\005\022\b\b\002\020\b\032\0020\003\022\b\b\002\020\t\032\0020\003¢\006\002\020\nJ\t\020\f\032\0020\003HÆ\003J\020\020\r\032\004\030\0010\005HÆ\003¢\006\002\020\016J\t\020\017\032\0020\003HÆ\003J\020\020\020\032\004\030\0010\005HÆ\003¢\006\002\020\016J\t\020\021\032\0020\003HÆ\003J\t\020\022\032\0020\003HÆ\003JN\020\023\032\0020\0002\b\b\002\020\002\032\0020\0032\n\b\002\020\004\032\004\030\0010\0052\b\b\002\020\006\032\0020\0032\n\b\002\020\007\032\004\030\0010\0052\b\b\002\020\b\032\0020\0032\b\b\002\020\t\032\0020\003HÆ\001¢\006\002\020\024J\023\020\025\032\0020\0032\b\020\026\032\004\030\0010\001HÖ\003J\t\020\027\032\0020\005HÖ\001J\016\020\030\032\0020\0032\006\020\031\032\0020\003J\t\020\032\032\0020\033HÖ\001R\024\020\004\032\004\030\0010\0058\006X\004¢\006\004\n\002\020\013R\020\020\006\032\0020\0038\006X\004¢\006\002\n\000R\020\020\002\032\0020\0038\006X\004¢\006\002\n\000R\024\020\007\032\004\030\0010\0058\006X\004¢\006\004\n\002\020\013R\020\020\b\032\0020\0038\006X\004¢\006\002\n\000R\020\020\t\032\0020\0038\006X\004¢\006\002\n\000¨\006\035"}, d2 = {"Lokhttp3/internal/ws/WebSocketExtensions;", "", "perMessageDeflate", "", "clientMaxWindowBits", "", "clientNoContextTakeover", "serverMaxWindowBits", "serverNoContextTakeover", "unknownValues", "(ZLjava/lang/Integer;ZLjava/lang/Integer;ZZ)V", "Ljava/lang/Integer;", "component1", "component2", "()Ljava/lang/Integer;", "component3", "component4", "component5", "component6", "copy", "(ZLjava/lang/Integer;ZLjava/lang/Integer;ZZ)Lokhttp3/internal/ws/WebSocketExtensions;", "equals", "other", "hashCode", "noContextTakeover", "clientOriginated", "toString", "", "Companion", "okhttp"})
/*     */ public final class WebSocketExtensions
/*     */ {
/*     */   @JvmField
/*     */   public final boolean perMessageDeflate;
/*     */   @JvmField
/*     */   @Nullable
/*     */   public final Integer clientMaxWindowBits;
/*     */   @JvmField
/*     */   public final boolean clientNoContextTakeover;
/*     */   @JvmField
/*     */   @Nullable
/*     */   public final Integer serverMaxWindowBits;
/*     */   @JvmField
/*     */   public final boolean serverNoContextTakeover;
/*     */   @JvmField
/*     */   public final boolean unknownValues;
/*     */   private static final String HEADER_WEB_SOCKET_EXTENSION = "Sec-WebSocket-Extensions";
/*     */   public static final Companion Companion = new Companion(null);
/*     */   
/*     */   public WebSocketExtensions(boolean perMessageDeflate, @Nullable Integer clientMaxWindowBits, boolean clientNoContextTakeover, @Nullable Integer serverMaxWindowBits, boolean serverNoContextTakeover, boolean unknownValues) {
/*  59 */     this.perMessageDeflate = perMessageDeflate; this.clientMaxWindowBits = clientMaxWindowBits; this.clientNoContextTakeover = clientNoContextTakeover; this.serverMaxWindowBits = serverMaxWindowBits; this.serverNoContextTakeover = serverNoContextTakeover; this.unknownValues = unknownValues;
/*     */   }
/*     */   public WebSocketExtensions() {
/*     */     this(false, null, false, null, false, false, 63, null);
/*     */   } public final boolean component1() {
/*     */     return this.perMessageDeflate;
/*     */   } @Nullable
/*     */   public final Integer component2() {
/*     */     return this.clientMaxWindowBits;
/*     */   } public final boolean component3() {
/*     */     return this.clientNoContextTakeover;
/*     */   } @Nullable
/*     */   public final Integer component4() {
/*     */     return this.serverMaxWindowBits;
/*     */   } public final boolean component5() {
/*     */     return this.serverNoContextTakeover;
/*     */   } public final boolean component6() {
/*     */     return this.unknownValues;
/*     */   } @NotNull
/*     */   public final WebSocketExtensions copy(boolean perMessageDeflate, @Nullable Integer clientMaxWindowBits, boolean clientNoContextTakeover, @Nullable Integer serverMaxWindowBits, boolean serverNoContextTakeover, boolean unknownValues) {
/*     */     return new WebSocketExtensions(perMessageDeflate, clientMaxWindowBits, clientNoContextTakeover, serverMaxWindowBits, serverNoContextTakeover, unknownValues);
/*     */   } @NotNull
/*     */   public String toString() {
/*     */     return "WebSocketExtensions(perMessageDeflate=" + this.perMessageDeflate + ", clientMaxWindowBits=" + this.clientMaxWindowBits + ", clientNoContextTakeover=" + this.clientNoContextTakeover + ", serverMaxWindowBits=" + this.serverMaxWindowBits + ", serverNoContextTakeover=" + this.serverNoContextTakeover + ", unknownValues=" + this.unknownValues + ")";
/*  83 */   } public final boolean noContextTakeover(boolean clientOriginated) { return clientOriginated ? 
/*  84 */       this.clientNoContextTakeover : 
/*     */       
/*  86 */       this.serverNoContextTakeover; } public int hashCode() { if (this.perMessageDeflate); if (this.clientNoContextTakeover); if (this.serverNoContextTakeover); if (this.unknownValues);
/*     */     return ((((1 * 31 + ((this.clientMaxWindowBits != null) ? this.clientMaxWindowBits.hashCode() : 0)) * 31 + 1) * 31 + ((this.serverMaxWindowBits != null) ? this.serverMaxWindowBits.hashCode() : 0)) * 31 + 1) * 31 + 1; } public boolean equals(@Nullable Object paramObject) { if (this != paramObject) {
/*     */       if (paramObject instanceof WebSocketExtensions) {
/*     */         WebSocketExtensions webSocketExtensions = (WebSocketExtensions)paramObject;
/*     */         if (this.perMessageDeflate == webSocketExtensions.perMessageDeflate && Intrinsics.areEqual(this.clientMaxWindowBits, webSocketExtensions.clientMaxWindowBits) && this.clientNoContextTakeover == webSocketExtensions.clientNoContextTakeover && Intrinsics.areEqual(this.serverMaxWindowBits, webSocketExtensions.serverMaxWindowBits) && this.serverNoContextTakeover == webSocketExtensions.serverNoContextTakeover && this.unknownValues == webSocketExtensions.unknownValues)
/*     */           return true; 
/*     */       } 
/*     */     } else {
/*     */       return true;
/*     */     } 
/*     */     return false; } @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\000\036\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\000\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\005\032\0020\0062\006\020\007\032\0020\bR\016\020\003\032\0020\004XT¢\006\002\n\000¨\006\t"}, d2 = {"Lokhttp3/internal/ws/WebSocketExtensions$Companion;", "", "()V", "HEADER_WEB_SOCKET_EXTENSION", "", "parse", "Lokhttp3/internal/ws/WebSocketExtensions;", "responseHeaders", "Lokhttp3/Headers;", "okhttp"}) public static final class Companion
/*     */   {
/*  98 */     @NotNull public final WebSocketExtensions parse(@NotNull Headers responseHeaders) throws IOException { Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders"); boolean compressionEnabled = false;
/*  99 */       Integer clientMaxWindowBits = (Integer)null;
/* 100 */       boolean clientNoContextTakeover = false;
/* 101 */       Integer serverMaxWindowBits = (Integer)null;
/* 102 */       boolean serverNoContextTakeover = false;
/* 103 */       boolean unexpectedValues = false;
/*     */       byte b;
/*     */       int i;
/* 106 */       for (b = 0, i = responseHeaders.size(); b < i; b++) {
/* 107 */         if (StringsKt.equals(responseHeaders.name(b), "Sec-WebSocket-Extensions", true)) {
/*     */ 
/*     */           
/* 110 */           String header = responseHeaders.value(b);
/*     */ 
/*     */           
/* 113 */           int pos = 0;
/* 114 */           while (pos < header.length()) {
/* 115 */             int extensionEnd = Util.delimiterOffset$default(header, ',', pos, 0, 4, null);
/* 116 */             int extensionTokenEnd = Util.delimiterOffset(header, ';', pos, extensionEnd);
/* 117 */             String extensionToken = Util.trimSubstring(header, pos, extensionTokenEnd);
/* 118 */             pos = extensionTokenEnd + 1;
/*     */ 
/*     */             
/* 121 */             if (StringsKt.equals(extensionToken, "permessage-deflate", true)) {
/* 122 */               if (compressionEnabled) unexpectedValues = true; 
/* 123 */               compressionEnabled = true;
/*     */ 
/*     */               
/* 126 */               while (pos < extensionEnd) {
/* 127 */                 int parameterEnd = Util.delimiterOffset(header, ';', pos, extensionEnd);
/* 128 */                 int equals = Util.delimiterOffset(header, '=', pos, parameterEnd);
/* 129 */                 String name = Util.trimSubstring(header, pos, equals);
/* 130 */                 String value = (equals < parameterEnd) ? 
/* 131 */                   StringsKt.removeSurrounding(Util.trimSubstring(header, equals + 1, parameterEnd), "\"") : 
/*     */                   
/* 133 */                   null;
/*     */                 
/* 135 */                 pos = parameterEnd + 1;
/*     */                 
/* 137 */                 if (StringsKt.equals(name, "client_max_window_bits", true)) {
/* 138 */                   if (clientMaxWindowBits != null) unexpectedValues = true; 
/* 139 */                   clientMaxWindowBits = (value != null) ? StringsKt.toIntOrNull(value) : null;
/* 140 */                   if (clientMaxWindowBits == null) unexpectedValues = true;  continue;
/*     */                 } 
/* 142 */                 if (StringsKt.equals(name, "client_no_context_takeover", true)) {
/* 143 */                   if (clientNoContextTakeover) unexpectedValues = true; 
/* 144 */                   if (value != null) unexpectedValues = true; 
/* 145 */                   clientNoContextTakeover = true; continue;
/*     */                 } 
/* 147 */                 if (StringsKt.equals(name, "server_max_window_bits", true)) {
/* 148 */                   if (serverMaxWindowBits != null) unexpectedValues = true; 
/* 149 */                   serverMaxWindowBits = (value != null) ? StringsKt.toIntOrNull(value) : null;
/* 150 */                   if (serverMaxWindowBits == null) unexpectedValues = true;  continue;
/*     */                 } 
/* 152 */                 if (StringsKt.equals(name, "server_no_context_takeover", true)) {
/* 153 */                   if (serverNoContextTakeover) unexpectedValues = true; 
/* 154 */                   if (value != null) unexpectedValues = true; 
/* 155 */                   serverNoContextTakeover = true;
/*     */                   continue;
/*     */                 } 
/* 158 */                 unexpectedValues = true;
/*     */               } 
/*     */ 
/*     */               
/*     */               continue;
/*     */             } 
/*     */             
/* 165 */             unexpectedValues = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 171 */       return new WebSocketExtensions(
/* 172 */           compressionEnabled, 
/* 173 */           clientMaxWindowBits, 
/* 174 */           clientNoContextTakeover, 
/* 175 */           serverMaxWindowBits, 
/* 176 */           serverNoContextTakeover, 
/* 177 */           unexpectedValues); }
/*     */ 
/*     */     
/*     */     private Companion() {}
/*     */   }
/*     */ }


/* Location:              /home/korvinius/git/plugins/Anhydrite/jar/okhttp.jar!/okhttp3/internal/ws/WebSocketExtensions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */