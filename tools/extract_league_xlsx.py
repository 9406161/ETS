import openpyxl,json,re,sys,collections
ws=openpyxl.load_workbook(sys.argv[1],data_only=True)['전체']
rows=[r for r in ws.iter_rows(min_row=2,values_only=True) if r[0] in("K5","K6","K7") and "50대" not in r[1]]
def lg_key(name,div):
    m=re.search(r'울산광역시\s*(중구|남구|동구|북구|울주군)?.*\[(.*?)\]',name)
    gu=m.group(1) or ""; sub=m.group(2)
    sub=re.sub(r'(2025 K5 울산광역시 League)','',sub); sub=sub.replace(" League","").replace("리그","").strip()
    return gu,sub
leagues=collections.OrderedDict(); teams={}; matches=[]
def tname(n):
    m=re.match(r'울산(중구|남구|동구|북구|울주군)(.+)',n)
    return (m.group(2).strip(), m.group(1)) if m else (n.replace("울산",""), "")
for r in rows:
    div=r[0]; gu,sub=lg_key(r[1],div)
    lid=f"u{div[1]}{gu}{sub}"
    if lid not in leagues:
        area=" ".join(x for x in [gu,sub] if x)
        leagues[lid]={"id":lid,"div":div,"area":area,"official":r[1]}
    for side in (7,10):
        full=r[side]; key=full+"|"+lid
        if key not in teams:
            nm,g=tname(full); teams[key]={"key":key,"name":nm,"gu":g,"official":full,"leagueId":lid,"div":div}
    note=r[13] if r[13] and not r[13][0].isdigit() else ""
    note=note.split(" ")[0] if note else ""
    matches.append({"leagueId":lid,"no":r[2],"date":r[3],"time":r[5],"venue":r[6],"home":r[7]+"|"+lid,"away":r[10]+"|"+lid,"hs":r[8],"as":r[9],"note":note,"dbl":note=="홈/어웨이몰수패"})
# rounds per league by date
for lid in leagues:
    ds=sorted(set(m["date"] for m in matches if m["leagueId"]==lid))
    for m in matches:
        if m["leagueId"]==lid: m["round"]=ds.index(m["date"])+1
names=collections.Counter(t["name"] for t in teams.values())
dup=[n for n,c in names.items() if c>1]
off=collections.Counter(t["official"] for t in teams.values())
for t in teams.values():
    if off[t["official"]]>1 and t["div"]!="K5": t["name"]=f'{t["name"]}({t["div"]})'
    elif t["name"] in dup and off[t["official"]]==1: t["name"]=f'{t["name"]}({t["gu"]})'
out={"id":"ulsan","region":"ulsan","season":"2025","stage":"울산 무대","source":"2025 K5~K7 울산 경기결과 (대한축구협회 JoinKFA 기록)","leagues":list(leagues.values()),"teams":list(teams.values()),"matches":matches}
json.dump(out,open(sys.argv[2],"w"),ensure_ascii=False)
print(len(leagues),len(teams),len(matches),"dup:",dup)
for l in leagues.values(): print(l["id"],l["div"],l["area"])
