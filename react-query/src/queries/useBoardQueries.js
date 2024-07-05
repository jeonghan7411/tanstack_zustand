import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import BOARD_API from "../api/Board";

// 게시물  리스트
export const useBoardList = () => {
  return useQuery({
    queryKey: ["boardList"],
    queryFn: BOARD_API.boardList,
    cacheTime: 60000,
  })
};

// 게시물  상세 
export const useBoardDetail = (idx) => {
  return useQuery({
    queryKey: ["board",idx],
    queryFn: () => BOARD_API.board(idx),
    enabled : !!idx // idx가 있을때만 실행
  })
};

export const useBoardCreate = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn:BOARD_API.createBoard,
    onSuccess: () => {
      queryClient.invalidateQueries(["boardList"])
    },
    onError: (error) => {
      console.error("Error  : ", error);
    },
  });
};

export const useBoardDelete = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn:(idx) => BOARD_API.deleteBoard(idx),
    onSuccess: () => {
      queryClient.invalidateQueries(["boardList"]);
    },
    onError:(error) => {
      console.error("Error : ", error);
    },
  });
};

export const useBoardUpdate = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn:(board) => BOARD_API.updateBoard(board),
    onSuccess: () => {
      queryClient.invalidateQueries(["boardList"]);
    },
    onError:(error) => {
      console.error("Error : ", error);
    }
  })
};