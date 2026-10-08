import json,sys
src,data,out=sys.argv[1:4]
s=open(src,encoding="utf-8").read(); d=json.load(open(data,encoding="utf-8"))
st=d["stage"]
for a,b in [("전국 무대",st),("전국 1위",st+" 1위"),("전국 ${slots.national.rank}위",st+" ${slots.national.rank}위"),
            ("ROOTS 시제품 · 데이터는 이 브라우저에만 저장됩니다",f"ROOTS 울산 시범판 · 경기 기록: {d['source']} · 글은 예시"),
            ("<title>","<title>")]:
    s=s.replace(a,b)
inj="<script>window.ROOTS_PILOT="+json.dumps(d,ensure_ascii=False).replace("</","<\\/")+";</script>\n<script>"
assert s.count("<script>\n\"use strict\";")==1
s=s.replace("<script>\n\"use strict\";",inj+"\n\"use strict\";",1)
open(out,"w",encoding="utf-8").write(s); print("ok",len(s))
