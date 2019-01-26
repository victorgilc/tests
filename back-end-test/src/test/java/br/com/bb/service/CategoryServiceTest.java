package br.com.bb.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.bb.Application;
import br.com.bb.dto.CategoryDTO;
import br.com.bb.filter.CategoryFilter;
import br.com.bb.handler.CategoryHandler;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class CategoryServiceTest {

	@Autowired
	private CategoryService service;

	@Mock
	private CategoryHandler handler;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void listByFilterNull() {
		exception.expect(IllegalArgumentException.class);
		service.listByFilter(null);
	}

	@Test
	public void listByFilterEmpty() {
		exception.expect(IllegalArgumentException.class);
		CategoryFilter filter = new CategoryFilter();
		service.listByFilter(filter);
	}

	@Test
	public void listByFilterEmptyToFind() {
		exception.expect(IllegalArgumentException.class);
		CategoryFilter filter = new CategoryFilter();
		filter.setToFind(StringUtils.EMPTY);
		service.listByFilter(filter);
	}

	@Test
	public void listByFilterNoContent() {
		exception.expect(NoSuchElementException.class);
		CategoryFilter filter = new CategoryFilter();
		filter.setToFind("Nada a ver");
		service.listByFilter(filter);
	}

	@Test
	public void listByFilterOK() {
		CategoryFilter filter = new CategoryFilter();
		filter.setToFind("E");
		CategoryDTO toReturn = service.listByFilter(filter);
		Assert.assertNotNull(toReturn);
		Assert.assertNotNull(toReturn.getId());
		Assert.assertNotNull(toReturn.getName());
	}

	@Test
	public void listAllOk() {
		List<CategoryDTO> toReturn = service.listAll();
		Assert.assertFalse(toReturn.isEmpty());
	}
}
