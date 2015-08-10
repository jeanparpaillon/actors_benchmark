module Main where

import Control.Monad
import Control.Concurrent
import System.Environment

process :: (Show a, Ord a, Num a) => MVar () -> MVar a -> MVar a -> IO ()
process r i o = do
  vi <- takeMVar i
  if vi > 0
     then (putMVar o $! vi - 1) >> process r i o
     else putMVar r ()

-- init a list of m element a cycle over it
cycleM :: Monad m => Int -> m a -> m [a]
cycleM a b = do
  r <- replicateM a b
  let r' = cycle r
  return r'

main ::  IO ()
main = do
  args <- getArgs
  let nbThreads = read (args !! 0)
  let initCptr = read (args !! 1)
  ret <- newEmptyMVar
  channels <- cycleM nbThreads newEmptyMVar -- IO [MVar a]
  let threads = (take nbThreads) $  zipWith (process ret) channels (tail channels) -- [IO ()]
  mapM_ forkIO threads
  putMVar (head channels) initCptr
  takeMVar ret
