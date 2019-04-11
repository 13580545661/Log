package com.weacadt.log.database;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.weacadt.log.data.DiaryItem;
import com.weacadt.log.data.TodoItem;

import com.weacadt.log.database.DiaryItemDao;
import com.weacadt.log.database.TodoItemDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig diaryItemDaoConfig;
    private final DaoConfig todoItemDaoConfig;

    private final DiaryItemDao diaryItemDao;
    private final TodoItemDao todoItemDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        diaryItemDaoConfig = daoConfigMap.get(DiaryItemDao.class).clone();
        diaryItemDaoConfig.initIdentityScope(type);

        todoItemDaoConfig = daoConfigMap.get(TodoItemDao.class).clone();
        todoItemDaoConfig.initIdentityScope(type);

        diaryItemDao = new DiaryItemDao(diaryItemDaoConfig, this);
        todoItemDao = new TodoItemDao(todoItemDaoConfig, this);

        registerDao(DiaryItem.class, diaryItemDao);
        registerDao(TodoItem.class, todoItemDao);
    }
    
    public void clear() {
        diaryItemDaoConfig.clearIdentityScope();
        todoItemDaoConfig.clearIdentityScope();
    }

    public DiaryItemDao getDiaryItemDao() {
        return diaryItemDao;
    }

    public TodoItemDao getTodoItemDao() {
        return todoItemDao;
    }

}