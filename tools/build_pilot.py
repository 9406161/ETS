"""시범판 만들기: python3 build_pilot.py index.html 출력.html 지역1.json [지역2.json ...]"""
import json,sys
src,out,*datas=sys.argv[1:]
regs=[json.load(open(d,encoding="utf-8")) for d in datas]
names={"ulsan":"울산","busan":"부산","daegu":"대구","gyeongnam":"경남","gyeongbuk":"경북"}
label="·".join(names.get(r["region"],r["region"]) for r in regs)
P={"id":"pilot-"+"-".join(r["region"] for r in regs),"season":"2025","source":"2025 K5~K7 경기결과 (대한축구협회 JoinKFA 기록)","label":label,"regions":regs}
s=open(src,encoding="utf-8").read()
s=s.replace("ROOTS 시제품 · 데이터는 이 브라우저에만 저장됩니다",f"ROOTS 시범판 ({label}) · 경기 기록: {P['source']} · 일반 글은 예시")
s=s.replace("<title>ROOTS 루츠</title>",f"<title>ROOTS 시범판 {label}</title>",1)
anchor="<script>\n\"use strict\";"; assert s.count(anchor)==1
s=s.replace(anchor,"<script>window.ROOTS_PILOT="+json.dumps(P,ensure_ascii=False).replace("</","<\\/")+";</script>\n"+anchor,1)
open(out,"w",encoding="utf-8").write(s); print("ok",label,len(s))
