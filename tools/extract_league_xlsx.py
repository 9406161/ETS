"""협회 K5~K7 경기결과 엑셀(전체 시트) → ROOTS 시범판용 JSON
사용: python3 extract_league_xlsx.py 원본.xlsx 지역id 지역이름(울산) 구군목록(공백구분) 출력.json"""
import openpyxl,json,re,sys,collections
src,rid,rname,dists,out=sys.argv[1:6]
dists=sorted(dists.split(),key=len,reverse=True)
ws=openpyxl.load_workbook(src,data_only=True)['전체']
rows=[r for r in ws.iter_rows(min_row=2,values_only=True) if r[0] in("K5","K6","K7") and "50대" not in (r[1] or "")]
def area_of(name,div):
    gu=next((d for d in dists if d in name),"") if div=="K7" else ""
    m=re.search(r'\[([^\]]*)\]',name); sub=""
    if m:
        t=m.group(1).replace("League","").replace("리그","").strip()
        if len(t)<=2: sub=t          # A·B·C 같은 조 이름만
    return gu,sub
def tname(full):
    n=re.sub(r'^'+rname,'',full).strip()
    gu=next((d for d in dists if n.startswith(d)),"")
    return (n[len(gu):].strip() or n), gu
leagues=collections.OrderedDict(); teams={}; matches=[]
for r in rows:
    div=r[0]; gu,sub=area_of(r[1],div); lid=f"{rid}{div[1]}{gu}{sub}"
    if lid not in leagues: leagues[lid]={"id":lid,"div":div,"area":" ".join(x for x in [gu,sub] if x),"official":r[1]}
    for side in (7,10):
        full=r[side]; key=full+"|"+lid
        if key not in teams:
            nm,g=tname(full); teams[key]={"key":key,"name":nm,"gu":g,"official":full,"leagueId":lid,"div":div}
    note=r[13] if r[13] and not str(r[13])[0].isdigit() else ""
    note=note.split(" ")[0] if note else ""
    matches.append({"leagueId":lid,"no":r[2],"date":str(r[3])[:10],"time":r[5],"venue":r[6],"home":r[7]+"|"+lid,"away":r[10]+"|"+lid,
                    "hs":r[8],"as":r[9],"note":note,"dbl":note=="홈/어웨이몰수패"})
for lid in leagues:
    ds=sorted(set(m["date"] for m in matches if m["leagueId"]==lid))
    for m in matches:
        if m["leagueId"]==lid: m["round"]=ds.index(m["date"])+1
names=collections.Counter(t["name"] for t in teams.values()); off=collections.Counter(t["official"] for t in teams.values())
for t in teams.values():
    if off[t["official"]]>1 and t["div"]!="K5": t["name"]+=f'({t["div"]})'
    elif names[t["name"]]>1 and off[t["official"]]==1: t["name"]+=f'({t["gu"] or t["div"]})'
json.dump({"region":rid,"leagues":list(leagues.values()),"teams":list(teams.values()),"matches":matches},open(out,"w",encoding="utf-8"),ensure_ascii=False)
print(rid,len(leagues),"리그",len(teams),"팀",len(matches),"경기")
for l in leagues.values(): print(" ",l["id"],l["div"],l["area"] or "-")
