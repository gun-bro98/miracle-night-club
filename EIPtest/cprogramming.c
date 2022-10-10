
// struct jsu{
//     char nae[12];
//     int os, db, hab, hhab;
// };

// int main() {
//     struct jsu st[3] = { {"데이터1", 95, 88},{"데이터2", 84, 91},{"데이터3", 86, 75}};
//     struct jsu *p;
//     p = &st[0];
//     (p+1)->hab = (p+1)->os + (p+2)->db;
//     (p+1)->hhab = (p+1)->hab + p->os + p->db;
//     printf("%d", (p+1)->hab + (p+1)->hhab);

}
// 포인터 문제 포인터 문제 빈도가 높으니 깊은 이해가 필요함
// 답 501
//-------------------------
// main(){
//     char* p = "KOREA";
//     printf("%s\n", p);
//     printf("%s\n", p+3);
//     printf("%s\n", *p);
//     printf("%s\n", *(p+3));
//     printf("%s\n", *p+2);
// }
//-------------------------


int r1(){
    return 4;
}

int r10(){
    return (30 + r1());
}

int r100(){
    return (200 + r10());
}

int main(){
    printf("%d/n", r100());
    return 0;
}

// 재귀 호출 순서 잘 파악하기
