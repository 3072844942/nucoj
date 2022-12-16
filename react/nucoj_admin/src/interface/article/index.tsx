
/**
 * 完成公告信息
 */
interface ArticleInfo {
    id: string | undefined,
    title: string,
    time: number,
    author: string,
    context: string
}

export type {ArticleInfo}