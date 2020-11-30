#include<bits/stdc++.h>
using namespace std;

const int N=2501;
const int M=15001;
int head[N], idx;

struct Edge
{
    int to, next, w;
};
Edge edge[M];

int t,c,ts,te;
int rs,re,ci;
int dis[N],inqueue[N];

void addEdge(int u,int v,int w) // 起点，终点，权值
{
    edge[idx].to=v;   // 该边的终点
    edge[idx].w=w;    // 权值
    edge[idx].next=head[u];
    head[u]=idx;
    idx++;
}

void spfa()
{
    memset(dis, 0x3f, sizeof(dis));//初始化
    queue<int> q;
    q.push(ts);
    inqueue[ts]=1;
    dis[ts]=0;
    while(!q.empty())
    {
        int t=q.front();
        q.pop();
        inqueue[t]=0;
        for(int i=head[t]; i!=-1; i=edge[i].next)
        {
            if(dis[edge[i].to]>dis[t]+edge[i].w)
            {
                dis[edge[i].to]=dis[t]+edge[i].w;
                if(!inqueue[edge[i].to])
                {
                    q.push(edge[i].to);
                    inqueue[edge[i].to]=1;
                }
            }
        }
    }
}

int main()
{
    ios::sync_with_stdio(false);
    for(int i=0; i<N; i++)
    {
    	head[i] = -1;
	}

    cin>>t>>c>>ts>>te;
    for(int i=0; i<c; i++)
    {
        cin>>rs>>re>>ci;
        addEdge(rs,re,ci);
        addEdge(re,rs,ci);
    }
    spfa();

    cout<<dis[te]<<endl;
    return 0;
}
