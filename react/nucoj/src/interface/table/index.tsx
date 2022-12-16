import {TablePaginationConfig} from "antd/es/table";
import {FilterValue} from "antd/es/table/interface";

interface TableParams {
    pagination?: TablePaginationConfig;
    sortField?: string;
    sortOrder?: string;
    filters?: Record<string, FilterValue>;
}

export type {TableParams}